package com.qa.democart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By email=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotPasswordLink=By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private By pageHeader=By.xpath("//h1/a[text()='Your Store']");
	private By homeMenu=By.xpath("//ul[@class='breadcrumb']//li");
	private By rightSideMenus=By.xpath("//div[@class='list-group']/a");
	private By registerLink = By.linkText("Register");

	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public AccountPage doLogin(String uname,String pwd) {
		driver.findElement(email).sendKeys(uname);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		return new AccountPage(driver);
	}
	
	public String  getTitle() {
		return driver.getTitle();
	}
	public boolean forgotPasswordLinkDisplayed() {
		return driver.findElement(forgotPasswordLink).isDisplayed();
	}
	public boolean loginPageHeaderDisplayed() {
		return driver.findElement(pageHeader).isDisplayed();
	}
	public int getHomeMenuLinks() {
		List<WebElement> el= driver.findElements(homeMenu);
		return el.size();
	}
	public List<String> getRightSideMenusText() {
		List<WebElement> el=driver.findElements(rightSideMenus);
		List <String> menuTexts=new ArrayList<String>();
		for(WebElement e:el ) {
			String text=e.getText();
			if(!text.isEmpty()) {
				menuTexts.add(text);
			}					
			
		}
		return menuTexts;
	}
	
	public RegistrtionPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrtionPage(driver);
	}
}
