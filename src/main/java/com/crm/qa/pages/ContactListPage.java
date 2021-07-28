package com.crm.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactListPage extends TestBase {

	public ContactListPage() {
		PageFactory.initElements(webdriver, this);
	}

	@FindBy(xpath = "//table/tbody/tr")
	private WebElement tablelist;

	public ArrayList<String> getList() {
		ArrayList<String> names=new ArrayList<String>();
		List nodelist = webdriver.findElements(By.xpath("//table/tbody/tr"));
		for (int a = 0; a < nodelist.size(); a++) {
			if(nodelist.get(a).equals("	Anu Sri"))
			{
				names.add(nodelist.get(a).toString());
			}
		}
		return names;
	}

}
