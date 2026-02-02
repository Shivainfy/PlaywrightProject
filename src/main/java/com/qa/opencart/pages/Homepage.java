package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class Homepage {
	private Page page;
	
private String SearchBox="div#search input";
private String SearchButton="div#search button";
private String MacValidate="div#content h1";
private String LoginLink="a:text('Login')";
private String MyAccountLink="(//span[normalize-space()='My Account'])[1]";

public Homepage(Page page){
	this.page=page;
}

public String GetTitle() {
	String titleIs=page.title();
	System.out.println(titleIs);
	return titleIs;
}

public String GetUrl() {
	String urlIs=page.url();
	System.out.println(urlIs);
	return urlIs;
}

public void doSearch(String producName) throws InterruptedException {
	page.locator(SearchBox).fill(producName);
	Thread.sleep(3000);
}

public void doClickSubmitBtn() {
	page.click(SearchButton);
}

public String ValidateMac() {
	String Mvalidate=page.textContent(MacValidate);
	return Mvalidate;
}

public LoginPage navigateToLoginPage() {
	page.click(MyAccountLink);
	page.click(LoginLink);
	return new LoginPage(page); //login page class constructor is waiting for the pageClass reference
}

}
