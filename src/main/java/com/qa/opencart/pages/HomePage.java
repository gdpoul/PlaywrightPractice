package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page; 
	
	//1. Declaraion: String Locators - Object Repository
	
	private String search="input[name='search']";
	private String searchIcon="div#search button";
	private String searchResultHeader="div#content h1";
	private String accountLink="span:text('My Account')";
	private String loginLink="a:text('Login')";
	
	//2. Initialization : Page Constructor
	
	public HomePage(Page page) {
		this.page = page;
	}
	
	//3. Page Actions/Method
	
	public String getHomePageTitle() {
		String title= page.title();
		System.out.println("home page title: "+title);
		return title;
	}
	
	public String getHomePageURL() {
		String url= page.url();
		System.out.println("home page url: "+url);
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);	
		return page.textContent(searchResultHeader);
	}
	public LoginPage navigateToLoginPage() {
		page.click(accountLink);
		page.click(loginLink);
		return new LoginPage(page);
	}
}
