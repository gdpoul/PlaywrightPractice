package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
//	1. String Locators or Object Repository
	private String emailId="#input-email";
	private String password="#input-password";
	private String loginBtn="//input[@value='Login']";
	private String forgotPwdLink="(//a[text()='Forgotten Password'])[1]";
    private String logoutlink="//a[@class='list-group-item'][normalize-space()='Logout']";
    
//	2. Page Constructor
	public LoginPage(Page page) {
		this.page=page;
	}
	
//	Page Actions/methods
	
	public String getLoginPageTitle() {
		return page.title();
	}
	
	public boolean isforgotPwdLinkExist() {
		return page.isVisible(forgotPwdLink);
	}
	
	public boolean doLogin(String appUn, String appPwd) {
		page.fill(emailId, appUn);
		page.fill(password, appPwd);
		page.click(loginBtn);
		if(page.isVisible(logoutlink)) {
			System.out.println("User has Logged in successfully ");
			return true;
		}
		else {
			System.out.println("User has not Logged in successfully ");
			return false;
		}
	}
}
