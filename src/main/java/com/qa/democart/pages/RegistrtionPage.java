package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.democart.utils.Constant;
import com.qa.democart.utils.ElementUtil;

public class RegistrtionPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By emailId=By.id("input-email");
	private By phone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	private By subscribeBtnYes=By.xpath("(//label[@class='radio-inline'])[1]");
	private By subscribeBtnNo=By.xpath("(//label[@class='radio-inline'])[2]");
	private By policyCheckBox=By.name("agree");
	private By continueBttn=By.xpath("//input[@class='btn btn-primary']");
	private By sucessMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public RegistrtionPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
				
	}
	
	public boolean newUserRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {
		eleUtil.doSendKeys(this.firstName , firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.emailId,email);
		eleUtil.doSendKeys(phone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(confirmPassword,password);
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeBtnYes);
		}else {
			eleUtil.doClick(subscribeBtnNo);
		}
		eleUtil.doClick(policyCheckBox);
		eleUtil.doClick(continueBttn);			
		String message=eleUtil.waitForElementPresence(sucessMessg, 5).getText();
		System.out.println(message);
		if(message.contains(Constant.REGISTER_SUCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}else {
			return false;
		}
	}
}
