package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	Actions actions;

	public HomePage() {
		PageFactory.initElements(webdriver, this);
	}

	@FindBy(className = "user-display")
	private WebElement lbluser;

	@FindBy(xpath = "//a[@href='/calendar']")
	private WebElement popupcal;

	@FindBy(xpath = "//span[text()='Contacts']")
	private WebElement lnkcontact;

	public String getUser() {
		return lbluser.getText();
	}

	public ContactPage openContact() {
		actions = new Actions(webdriver);
		actions.moveToElement(popupcal).build().perform();
		lnkcontact.click();
		return new ContactPage();
	}
	
	
}
