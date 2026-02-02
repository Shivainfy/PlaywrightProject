package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	
	@Test(priority=1)
	public void LoginPageNavigationTest() {
		loginPage=home.navigateToLoginPage();
		String ActualTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(ActualTitle, AppConstants.HOME_PAGE_TITLE);
	}
	@Test(priority=2)
	public void isForgotPasswordExistsTest() {
		Assert.assertTrue(loginPage.isForgotPWLinkExists());
	}
	@Test(priority=3)
	public void doLogin() {
		Assert.assertTrue(loginPage.doLogin(props.getProperty("Username").trim(), props.getProperty("Password")));
	}

}
