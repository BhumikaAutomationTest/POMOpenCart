package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.democart.pages.AccountPage;
import com.qa.democart.utils.Constant;
import com.qa.democart.utils.Errors;

import io.qameta.allure.Description;

public class LoginTest extends BaseTest {
	
	@Description("Login page title test")
	@Test(priority=1)
	public void titleTest() {
		String title=login.getTitle();
		Assert.assertEquals( title,Constant.LOGIN_PAGE_TITLE,Errors.TITLE_ERROR);
		
	}
	@Description("Forgot password link test")
	@Test(priority=2)
	public void forgotPasswordLinkTest() {
		Assert.assertTrue(login.forgotPasswordLinkDisplayed());
	}
	@Description("Login page header test")
	@Test(priority=3)
	public void logiPageHeaderTest() {
		Assert.assertTrue(login.loginPageHeaderDisplayed());		
	}	
	@Description("Menu links test")	
	@Test(priority=4)
	public void righSideMenuLinks() {
		List<String> expectedMenulist= Arrays.asList("Login","Register","Forgotten Password","My Account","Address Book",
				"Wish List","Order History","Downloads","Recurring payments","Reward Points","Returns","Transactions","Newsletter");
		List<String> Listmenus=login.getRightSideMenusText();
		Assert.assertEquals(expectedMenulist, Listmenus,Errors.MENU_LINKS_ERR);
	}
	@Description("Login using valid user name password test")
	@Test(priority=5)
	public void loginTest() {
		AccountPage accPage=login.doLogin(props.getProperty("username"), props.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutExist());
	}


}
