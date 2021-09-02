package com.qa.democart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {		
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	private By header=By.cssSelector("div#content h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By priceMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private Map<String, String> productInfoMap;
	
	public String getProductHeader() {
		return eleUtil.doGetText(header);
	}
	public int getProductImgCount() {
		return eleUtil.getElements(productImages).size();
	}
	public Map<String, String> getProductInfo() {
		productInfoMap=new HashMap<String,String>();
		productInfoMap.put("name", getProductHeader());
		List<WebElement> list=eleUtil.getElements(productMetaData);
		System.out.println("Product meta data list -" +list.size());
		for(WebElement e:list) {
			String meta[]=e.getText().split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productInfoMap.put(metaKey, metaValue);		
		}
		List <WebElement> priceList=eleUtil.getElements(priceMetaData);
		System.out.println("Total product price list -" +priceList.size());
		String price =priceList.get(0).getText().trim();
		String exTaxPrice=priceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exTaxPrice", exTaxPrice);
		return productInfoMap;
	}

}
