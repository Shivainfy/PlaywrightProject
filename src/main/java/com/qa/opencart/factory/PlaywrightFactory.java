package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	Playwright playwright;
	Browser browser;
	BrowserContext context;
	Page page;
	
	FileInputStream fis;
	Properties props;
	
	private static ThreadLocal<Playwright> Tplaywright = new ThreadLocal<>();
	private static ThreadLocal<Browser> Tbrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> TBcontext = new ThreadLocal<>();
	private static ThreadLocal<Page> Tpage = new ThreadLocal<>(); 
	
	public static Playwright getPlaywright() {
		return Tplaywright.get();
	}
	
	public static Browser getBrowser() {
		return Tbrowser.get();
	}
	
	public static BrowserContext getBrowserContext() {
		return TBcontext.get();
	}
	
	public static Page getPage() {
		return Tpage.get();
	}
	
	public Page initBrowser(Properties props) {
		String browserName=props.getProperty("browser").trim();
		System.out.println("Browser name is :"+browserName);
//		playwright = Playwright.create();
		Tplaywright.set(Playwright.create());
		switch(browserName.toLowerCase()) {
		case "chromium":
//			browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
			Tbrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(1000)));
			break;
		case "firefox":
//			browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Tbrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(1000)));
			break;
		case "safari":
//			browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Tbrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(1000)));
			break;
		case "chrome":
//			browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHandleSIGHUP(false));
			Tbrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(true).setSlowMo(1000)));
		default: System.out.println("Please pass correct browsername");
			break;
		}
		
//		context=browser.newContext();
		TBcontext.set(getBrowser().newContext());
//		page=context.newPage();
		Tpage.set(getBrowserContext().newPage());
//		page.navigate(props.getProperty("url"));
		getPage().navigate(props.getProperty("url").trim());
		return getPage();
	}
	
	public Properties init_Prop() {
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/config/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		props = new Properties();
		try {
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
		}
	
	public static String takeScreenshot() {
		 if (getPage() == null) {
		        System.out.println("⚠ Page is NULL, screenshot skipped");
		        return null;
		    }

		    String path = System.getProperty("user.dir")
		            + "/screenshot/" + System.currentTimeMillis() + ".png";

		    getPage().screenshot(new Page.ScreenshotOptions()
		            .setPath(Paths.get(path))
		            .setFullPage(true));

		    return path;
			
	}
	
}
