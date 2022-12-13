package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest {

	
	@Test(priority = 1)
	public void homePageTitleTest() {
		String actualTitle=homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE );
	}
	
	@Test(priority = 2)
	public void homePageUrlTest() {
		String actualUrl=homePage.getHomePageURL();
		Assert.assertEquals(actualUrl, prop.getProperty("url"));
	}
	
	@DataProvider
	public Object [][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"iphone"},
			{"Samsung"}
			
		};
	}
	@Test(priority=3,dataProvider = "getProductData")
	public void searchTest(String productName) {
		String actualHeader=homePage.doSearch(productName);
		Assert.assertEquals(actualHeader, "Search - "+productName);
	}
}
