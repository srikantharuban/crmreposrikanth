package com.crm.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.CredentialsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.Utils;

public class ContactTest extends TestBase {

	LoginPage loginpage;
	CredentialsPage credentialspage;
	HomePage homepage;
	ContactPage contactpage;

	public ContactTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		init();
		loginpage = new LoginPage();
		credentialspage = loginpage.getCredentialsPage();
		homepage = credentialspage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactpage = homepage.openContact();
	}

	@DataProvider(name = "data-provider")
	public Object[][] getCRMContactData() {
		Object data[][] = Utils.getExcelData("Contacts");
		return data;
	}
	
	@Test(priority = 1, dataProvider = "data-provider")
	public void contactTest(String name, String lastname, String staddress, String city, String state,
			String postalcode, String category, String status, String phone, String email) {
		contactpage.createContact(name, lastname, staddress, city, state, postalcode, category, status, phone, email);
		Assert.assertEquals(contactpage.getCreateContactTitle(), "Create New Contact");
		Assert.assertTrue(contactpage.getIconTrue());
		Assert.assertEquals(contactpage.getCreatedUser(name, lastname), name + " " + lastname);
	}

	@AfterMethod
	public void tearDown() {
		webdriver.quit();
	}
}
