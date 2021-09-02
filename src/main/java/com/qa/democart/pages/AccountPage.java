package com.qa.democart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.utils.Constant;
import com.qa.democart.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By logOutLink=By.linkText("Logout");
	private By accountSession= By.cssSelector("#content h2");
	private By searchTextBox=By.xpath("//input[@type='text' and @name='search']");
	private By searchBttn=By.cssSelector("div#search button");

	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccountPageTitle() {
		return eleUtil.waitForTitle(Constant.ACCOUNT_PAGE_TITLE, 5);
	}
	public boolean isLogoutExist() {
		return eleUtil.doIsDisplayed(logOutLink);
	}

	public boolean isSearchTextExist() {
		return eleUtil.doIsDisplayed(searchTextBox);
	}
	public List<String> getAccountSectionsText() {
		List<WebElement> accountSections = eleUtil.getElements(accountSession);
		List<String> sectionsTextList= new ArrayList<String>();
		for(WebElement e:accountSections) {
			sectionsTextList.add(e.getText());
		}		
		return sectionsTextList;		
	}
	public ResultPage doSearch(String productName) {
		System.out.println("Product name is -" +productName);
		eleUtil.doSendKeys(searchTextBox, productName);
		eleUtil.doClick(searchBttn);
		return new ResultPage(driver);
	}
}
