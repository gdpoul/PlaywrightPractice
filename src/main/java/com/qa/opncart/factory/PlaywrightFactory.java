
package com.qa.opncart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	Playwright playwright;
	Browser browser;
	public static BrowserContext browserContext;
	Page page;
	Properties prop;

	private static ThreadLocal<Browser> tlBrower= new ThreadLocal<>();
	public static ThreadLocal<BrowserContext> tlbrowserContext= new ThreadLocal<>();
	private static ThreadLocal<Page> tlpage= new ThreadLocal<>();	
	private static ThreadLocal<Playwright> tlplaywright= new ThreadLocal<>();
	
	
	public static Playwright getPlaywright() {
		return tlplaywright.get();
	}
	
	public static Browser getBrowser() {
		return tlBrower.get();
	}
	
	public static BrowserContext getBrowserContext() {
		return tlbrowserContext.get();
	}
	
	public static Page getPage() {
		return tlpage.get();
	}
	
	public Page initBrowser(Properties prop) {
		String browserName=prop.getProperty("browser").trim();
		System.out.println("browser name= " + browserName);
//		playwright = Playwright.create();
		tlplaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {
		case "chromium":
//			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrower.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "firefox":
//			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrower.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "safari":
//			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrower.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "chrome":
//			browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrower.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;

		case "edge":
//			browser = playwright.chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false));
			tlBrower.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
			break;

		default:
			System.out.println("please pass the right browser name......");
			break;
		}
		
		tlbrowserContext.set(getBrowser().newContext());
		tlpage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
        return getPage();
		
//		browserContext = browser.newContext();
//		page = browserContext.newPage();
//		page.navigate(prop.getProperty("url"));
//		return page;
	}

	/*
	 * this method is used to initialize the properties from config file
	 */
	public Properties init_prop() {
		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	/**
	 * take screenshot
	 * 
	 */

	public static String takeScreenshot() {
		
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return path;
		
//		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
//		String base64Path = Base64.getEncoder().encodeToString(buffer);		
//		return base64Path;
	}


}
