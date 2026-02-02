package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	Page page;
	
//	1. String locators;
	private String Username="#input-email";
	private String Password="#input-password";
	private String ForgotPassword="div[class='form-group'] a";
	private String LoginButton="//input[@type='submit']";
	private String LogoutLink="//a[@class='list-group-item'][normalize-space()='Logout']";
	
//	2. Page constructors
	public LoginPage(Page page) {
		this.page = page;
	}
//	3. Page actions/methods
	public String getLoginPageTitle() {
		String Ltitle=page.title();
		return Ltitle;
	}
	
	public boolean isForgotPWLinkExists() {
		return page.isVisible(ForgotPassword);
//		return true;
	}
	
	public boolean doLogin(String usename, String password) {
		page.fill(Username, usename);
		page.fill(Password, password);
		page.click(LoginButton);
		if(page.isVisible(LogoutLink)) {
			System.out.println("User logged in successfully.......");
			return true;
		}
		return false;
	}
}
