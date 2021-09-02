package com.qa.democart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.democart.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil= new JavaScriptUtil(driver);
	}

	/**
	 * @param locator
	 * @return Method will return web element
	 */
	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight.trim())) {
			jsUtil.flash(element);
		}
		return element;
	}

	/**
	 * @param locator
	 * @return list of web elements
	 */
	public List<WebElement> getElements(By locator) {
		List<WebElement> eleList = driver.findElements(locator);
		return eleList;
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	/**
	 * @param locator
	 * @return string value.
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * @param locator
	 * @param attribute
	 * @return string attribute value
	 */
	public String doGetAttributeValue(By locator, String attribute) {
		return getElement(locator).getAttribute(attribute);
	}

	/**
	 * @param locator
	 * @return boolean value
	 */
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();

	}

	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public void clickOnElement(By locator, String value) {
		List<WebElement> eleList = getElements(locator);
		System.out.println("Size of elementList is ->" + eleList.size());
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public List<String> getLinksTextList(By locator) {
		List<String> linkText = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				linkText.add(text);
			}
		}
		return linkText;

	}

	public boolean isElementExist(By locator) {
		List<WebElement> lisEle = getElements(locator);
		if (lisEle.size() > 0) {
			System.out.println("Element is present");
			return true;
		}
		System.out.println("Element is not present");
		return false;
	}

	/*********** Drop Down Util (Select) *********************/

	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public List<String> getDropDownOptionList(By locator) {
		List<String> optionValList = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionList = select.getOptions();
		System.out.println("Size of option list is -> " + optionList.size());
		for (WebElement e : optionList) {
			String text = e.getText();
			optionValList.add(text);
		}
		return optionValList;
	}

	public void selectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionList = select.getOptions();
		System.out.println("List size is ->" + optionList.size());
		for (WebElement e : optionList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/************************* Action Class Utilities **************************/

	public void doActionSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).perform();

	}

	public void doActionClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
	}

	public void twoLevelMenuHandle(By parentMenu, By childMenu) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();
	}

	public void threeLevelMenuHandle(By parentMenu1, By parentMenu2, By childMenu) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(parentMenu1)).perform();
		Thread.sleep(2000);
		action.moveToElement(getElement(parentMenu2)).perform();
		getElement(childMenu).click();
	}

	/**************** waitUtil ************************************/
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		String text = waitForAlert(timeOut).getText();
		return text;
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public void sendKeysOnAlert(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);
	}

	public String waitForTitleContains(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.titleContains(title))) {
			return driver.getTitle();
		}
		return null;
	}

	public String waitForTitle(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		return null;
	}

	public String waitForUrlContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForFullUrl(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public void waitForFrameAndSwitchToIt(String frameName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public void waitForFrameAndSwitchToIt(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameAndSwitchToIt(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitchToIt(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(timeOut))
				.pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebDriver waitForFrameWithFluentWait(By locator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(timeOut))
				.pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public WebElement retryingElement(By locator, int maxAttemt) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < maxAttemt) {
			try {
				element = driver.findElement(locator);
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("element is not found in attempt : " + (attempts + 1));
			}
			attempts++;

		}
		return element;
	}

}
