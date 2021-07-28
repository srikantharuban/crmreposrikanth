package com.crm.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.CredentialsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginTest extends TestBase {
	
	LoginPage loginpage;
	CredentialsPage credentialspage;
	HomePage homepage;
	

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		init();
		loginpage=new LoginPage();	
	}

	@Test(priority = 1)
	public void titleVerificationTest()
	{
		Assert.assertEquals(loginpage.getHeading(), "Free CRM");
	}
	
	@Test(priority = 2)
	public void loginTest() {
		credentialspage=loginpage.getCredentialsPage();
		homepage=credentialspage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.getUser(), prop.getProperty("user"));
	}
	
	

	@AfterMethod
	public void tearDown() {
		webdriver.quit();
	}

}
