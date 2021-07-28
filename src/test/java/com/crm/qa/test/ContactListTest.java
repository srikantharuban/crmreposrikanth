package com.crm.qa.test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactListPage;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.CredentialsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.Utils;

public class ContactListTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	CredentialsPage credentialspage;
	ContactPage contactpage;
	ContactListPage contactlistpage;

	public ContactListTest() {
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

	@DataProvider(name = "datapr")
	public Object[][] dataprovider() {
		Object[][] data = Utils.getExcelData("Contacts");
		return data;
	}

	@Test(dataProvider = "datapr")
	public void testContacts(String name, String lastname, String staddress, String city, String state,
			String postalcode, String category, String status, String phone, String email) {

		ArrayList<String> elementlist = contactpage.getList(name, lastname);
		if (elementlist.size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		
	}
	
	@AfterMethod
	public void tearDown() {
		webdriver.quit();
	}
}
