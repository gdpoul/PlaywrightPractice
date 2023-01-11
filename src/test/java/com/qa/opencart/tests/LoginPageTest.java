package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority=1)
	public void loginPageNavigationTest() {
		loginPage=homePage.navigateToLoginPage();
		String actualLoginPageTitle=loginPage.getLoginPageTitle();	
		System.out.println("page actual title is: "+actualLoginPageTitle);
		Assert.assertEquals(actualLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void forgotPwdLinkWxistTest() {
		Assert.assertTrue(loginPage.isforgotPwdLinkExist());
	}

		
	@Test(priority = 3)
	public void appLoginTest() {
		boolean status = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(status);
	}
	
}
