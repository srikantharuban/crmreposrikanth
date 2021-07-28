package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class CredentialsPage extends TestBase {

	public CredentialsPage() {
		PageFactory.initElements(webdriver, this);
	}

	@FindBy(name = "email")
	private WebElement txtusername;
	
	@FindBy(name="password")
	private WebElement txtpassword;
	
	@FindBy(xpath="//div[text()='Login']")
	private WebElement btnsubmit;
	
	
	public HomePage login(String username,String password)
	{
		txtusername.sendKeys(username);
		txtpassword.sendKeys(password);
		btnsubmit.click();
		return new HomePage();
	}
}
