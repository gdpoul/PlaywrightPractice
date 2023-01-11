package com.qa.opencart.base;

import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opncart.factory.PlaywrightFactory;

public class BaseTest {

	PlaywrightFactory pf;
	Page page;
	
	public Properties prop;
	public HomePage homePage;
	public LoginPage loginPage;
	
	
	@Parameters("browser")
	@BeforeTest
	public void setup(String browserName) {
		pf=new PlaywrightFactory();
		prop=pf.init_prop();
		
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		page=pf.initBrowser(prop);
		homePage=new HomePage(page);
		
//		PlaywrightFactory.browserContext.
//				tracing().start(new Tracing.StartOptions().
//						setScreenshots(true).setSnapshots(true));
	}
	
	@AfterTest
	public void tearDown() {
		
//		PlaywrightFactory.browserContext.
//		        tracing().stop(new Tracing.StopOptions().
//		        		setPath(Paths.get("traceTest.zip")));

		page.context().browser().close();
	}


}
