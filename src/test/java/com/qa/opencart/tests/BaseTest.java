package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.democart.factory.DriverFactory;
import com.qa.democart.pages.AccountPage;
import com.qa.democart.pages.LoginPage;
import com.qa.democart.pages.ProductInfoPage;
import com.qa.democart.pages.RegistrtionPage;
import com.qa.democart.pages.ResultPage;

public class BaseTest {
	WebDriver driver;
	Properties props;
	DriverFactory df;
	LoginPage login;
	AccountPage accPage;
	ResultPage resultPage;
	ProductInfoPage productInfoPage;
	RegistrtionPage regiPage;
	@BeforeTest
	public void setUp() {	
		df=new DriverFactory();
		props=df.initProperties();
		driver=df.initDriver(props);
		login=new LoginPage(driver);	
	}
	@AfterTest()
	public void tearDown() {
		driver.quit();
	}
}
