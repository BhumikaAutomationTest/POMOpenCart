package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest {
	@BeforeClass
	public void productInfoSetUp() {
		accPage=login.doLogin(props.getProperty("username"), props.getProperty("password"));
	}
	
	@Test
	
	public void productImageTest() {
		resultPage=accPage.doSearch("iMac");
		productInfoPage=resultPage.selectMainProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImgCount(),3);
	}
	
	@Test
	public void productInfoDetailsTest() {
		resultPage=accPage.doSearch("MacBook");
		productInfoPage=resultPage.selectMainProduct("MacBook Air");
		Map<String,String> accProductInforMap=productInfoPage.getProductInfo();
		Assert.assertEquals(accProductInforMap.get("name"),"MacBook Air");
		Assert.assertEquals(accProductInforMap.get("Brand"), "Apple");
		Assert.assertEquals(accProductInforMap.get("Product Code"), "Product 17");
		Assert.assertEquals(accProductInforMap.get("price"), "$1,000.00");
	}

}
