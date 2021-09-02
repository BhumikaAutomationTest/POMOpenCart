package com.qa.democart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.utils.ElementUtil;

public class ResultPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	public ResultPage(WebDriver driver) {
		// TODO Auto-generated constructor stub	
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	private By resultTable=By.cssSelector("div.caption a");
	private By searchHeader=By.cssSelector("div#content h1");

	public String getSearchPageHeader() {
		return eleUtil.doGetText(searchHeader);
	}
	
	public int getSearhProductListCount() {
		return eleUtil.getElements(resultTable).size();
	}
	
	public ProductInfoPage selectMainProduct(String mainProductName) {
		List<WebElement> searchList=eleUtil.getElements(resultTable);
		for(WebElement e:searchList) {
			if(e.getText().trim().equals(mainProductName)){
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
