package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest{

	@Test(priority=1)
	public void HomepageLogin() {
		String title=home.GetTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE); // page title reading it from App constants class
	}
	
	@Test(priority=2)
	public void HomepageGetUrl() {
		String url=home.GetUrl();
		Assert.assertEquals(url, props.getProperty("url"));
	}
	
	@Test(priority=3)
	public void HomepageSearch() throws InterruptedException {
		home.doSearch("Mac");
	}
	
	@Test(priority=4)
	public void HomepageButton() {
		home.doClickSubmitBtn();
	}
	
}
