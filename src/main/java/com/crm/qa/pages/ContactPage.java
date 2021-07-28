package com.crm.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactPage extends TestBase {

	public ContactPage() {
		PageFactory.initElements(webdriver, this);
	}

	@FindBy(xpath = "//button[text()='Create']")
	private WebElement btncreate;

	@FindBy(xpath = "//div[text()='Create New Contact']")
	private WebElement lblcreatecontact;

	@FindBy(name = "first_name")
	private WebElement txtfirstname;

	@FindBy(name = "last_name")
	private WebElement txtlastname;

	@FindBy(css = "input[placeholder='Email address']")
	private WebElement txtemail;

	@FindBy(css = "input[placeholder='Number']")
	private WebElement txtphone;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnsave;

	@FindBy(name = "address")
	private WebElement txtaddress;

	@FindBy(name = "city")
	private WebElement txtcity;

	@FindBy(name = "state")
	private WebElement txtstate;

	@FindBy(name = "zip")
	private WebElement txtzip;

	@FindBy(className = "visible menu transition")
	private WebElement dropcatalog;

	@FindBy(xpath = "//div[@role='listbox' and @name='category']")
	private WebElement listcategory;

	@FindBy(xpath = "//div[@name='status']")
	private WebElement dropstaus;

	@FindBy(xpath = "//i[@class='large user red icon']")
	private WebElement usericon;

	@FindBy(xpath = "//table/tbody/tr")
	private WebElement tablelist;

	public void createContact(String name, String lastname, String staddress, String city, String state,
			String postalcode, String category, String status, String phone, String email) {
		btncreate.click();
		txtfirstname.sendKeys(name);
		txtlastname.sendKeys(lastname);
		txtaddress.sendKeys(staddress);
		txtcity.sendKeys(city);
		txtstate.sendKeys(state);
		txtzip.sendKeys(postalcode);
		txtphone.sendKeys(phone);
		txtemail.sendKeys(email);
		JavascriptExecutor executor = (JavascriptExecutor) webdriver;
		executor.executeScript("arguments[0].click();", listcategory);
		List<WebElement> options = webdriver.findElements(
				By.xpath("//div[@class='visible menu transition']//div[contains(@name,'category')]//span"));
		for (WebElement option : options) {
			if (option.getText().equals(category)) {
				executor.executeScript("arguments[0].click();", option);
			}
		}

		executor.executeScript("arguments[0].click();", dropstaus);
		List<WebElement> statusoptions = webdriver
				.findElements(By.xpath("//div[@class='visible menu transition']//div[@name='status']/span"));
		for (WebElement statusoption : statusoptions) {
			if (statusoption.getText().equals(status)) {
				executor.executeScript("arguments[0].click();", statusoption);
			}
		}
		btnsave.click();
	}

	public ArrayList<String> getList(String name,String lastname) {
		ArrayList<String> names = new ArrayList<String>();
		String sname=name + " " + lastname;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> nodelist = webdriver.findElements(By.xpath("//table/tbody/tr"));
		for (int a = 1; a <= nodelist.size(); a++) {
			String value = webdriver.findElement(By.xpath("//table/tbody/tr[" + a + "]/td[2]/a")).getText();
			if (value.equals(sname)) {
				names.add(value);
			}
		}
		return names;
	}

	public boolean getIconTrue() {
		return usericon.isDisplayed();
	}

	public String getCreatedUser(String firstname, String lastname) {
		String username = firstname + " " + lastname;
		WebElement user = webdriver.findElement(
				By.xpath("//div[contains(@class,'ui header item mb5 light-black')][text()='" + username + "']"));
		return user.getText();
	}

	public String getCreateContactTitle() {
		return lblcreatecontact.getText();
	}

}
