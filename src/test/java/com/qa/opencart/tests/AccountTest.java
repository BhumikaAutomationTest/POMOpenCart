package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.democart.utils.Constant;
import com.qa.democart.utils.Errors;

import io.qameta.allure.Description;

public class AccountTest extends BaseTest {
	
	@BeforeClass
	public void accPageSet() {
		accPage=login.doLogin(props.getProperty("username"), props.getProperty("password"));
	}
	@Description("Account page title test")
	@Test(priority=1)
	public void accountPageTitleTest() {
		String title=login.getTitle();
		Assert.assertEquals(title, Constant.ACCOUNT_PAGE_TITLE,Errors.TITLE_ERROR);
	}
	@Description("Logout link test")
	@Test(priority=2)
	public void logOutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutExist(),Constant.LINKISNOTPRESENT);
	}
	@Description("Search text box test")
	@Test(priority=3)
	public void searchTextBoxTest() {
		Assert.assertTrue(accPage.isSearchTextExist());
	}
	@Description("Account section test")
	@Test(priority=4)
	public void accountSectionsTest() {
		List<String> expectedList= Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
		Assert.assertEquals(expectedList, accPage.getAccountSectionsText());
	}
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
				{"MacBook Pro"},
				{"MacBook Air"},
				{"Apple"}
				};
	}
	@Description("Search product  test")
	@Test(priority=5, dataProvider = "getSearchData")	
	public void searchProductTest(String productName) {
		resultPage=accPage.doSearch(productName);
		String resultHeader=resultPage.getSearchPageHeader();
		System.out.println("Result header is -" +resultHeader);
		Assert.assertTrue(resultHeader.contains(productName));		
	}
	
	@DataProvider
	public Object[][] getProductSelectData(){
		return new Object[][] {{"Macbook", "MacBook Air"},
			{"Macbook","MacBook Pro"},
			{"Apple","Apple Cinema 30\""}
			};	
	}
	
	@Description("Select main product test")
	@Test(priority=6 ,dataProvider="getProductSelectData")
	public void selectProductTest(String productName,String mainProduct) {
		resultPage=accPage.doSearch(productName);
		productInfoPage=resultPage.selectMainProduct(mainProduct);
		String header=productInfoPage.getProductHeader();
		System.out.println("Search product header is -" +header);
		Assert.assertEquals(header, mainProduct);
	}
}
