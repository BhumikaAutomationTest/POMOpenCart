package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.democart.utils.Constant;
import com.qa.democart.utils.ExcelUtil;

public class RegistrationTest extends BaseTest {
	@BeforeClass
	public void registrationSetUp() {
		regiPage=login.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random random=new Random();
		String email="testautomation"+random.nextInt(3000)+"@gmail.coml";
		return email;		
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return ExcelUtil.getTestData(Constant.REGISTER_SHEET_NAME);
	}
	@Test(dataProvider="getTestData")
	public void registrationTest(String firstName, String lastName, String telephone,
			String password, String subscribe) {
			Assert.assertTrue(regiPage.newUserRegistration(firstName, lastName, getRandomEmail(), telephone,
					 password,  subscribe));
	}
}
