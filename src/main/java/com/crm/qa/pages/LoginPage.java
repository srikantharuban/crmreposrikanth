package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	public LoginPage() {
		PageFactory.initElements(webdriver, this);
	}

	@FindBy(xpath = "//span[text()='Log In']")
	private WebElement btnlogin;

	@FindBy(css = "h1[class=txt-white]")
	private WebElement lblheading;

	public CredentialsPage getCredentialsPage() {
		btnlogin.click();
		return new CredentialsPage();
	}

	public String getHeading() {
		return lblheading.getText();
	}

}
