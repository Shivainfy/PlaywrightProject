package com.qa.opencart.tests;

import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.Homepage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	PlaywrightFactory pf;
	Page page;
	Homepage home;
	LoginPage loginPage;
	Properties props;
	
	
	@BeforeClass
	public void SetUp() {
		pf=new PlaywrightFactory();
		props=pf.init_Prop();
		page=pf.initBrowser(props); // passing entire prop object reference to initBrowser method instead of "chromium" directly
		home=new Homepage(page);
	}
	
	@AfterClass
	public void tearDown() {
//		page.context().browser().close();
		
//		if (PlaywrightFactory.getBrowserContext() != null) {
//	        PlaywrightFactory.getBrowserContext().close();
//	    }
//	    if (PlaywrightFactory.getBrowser() != null) {
//	        PlaywrightFactory.getBrowser().close();
//	    }
//	    if (PlaywrightFactory.getPlaywright() != null) {
//	        PlaywrightFactory.getPlaywright().close();
//	    }
	}

}
