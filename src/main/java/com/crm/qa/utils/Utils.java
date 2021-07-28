package com.crm.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class Utils extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 30;
	static WebDriverWait waitdriver;
	static Workbook workbook;
	static Sheet sheet;
	static FileInputStream file;
	static Object object[][];
	static String EXCEL_FILE_PATH="C:\\Users\\srikantharubanm\\eclipse-workspace\\qacrm\\src\\main\\java\\com\\crm\\qa\\testdata\\CRMData.xlsx";

	public static void explicitWait(WebElement webelement) {
		waitdriver = new WebDriverWait(webdriver, 20);
		waitdriver.until(ExpectedConditions.invisibilityOf(webelement));
	}

	public static void switchframe(int index) {
		webdriver.switchTo().frame(index);
	}

	public static void switchframe(String nameorid) {
		webdriver.switchTo().frame(nameorid);
	}

	public static void switchframe(WebElement frameelement) {
		webdriver.switchTo().frame(frameelement);
	}


	public static Object[][] getExcelData(String sheetname) {
		try {
			file = new FileInputStream(EXCEL_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			try {
				workbook = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = workbook.getSheet(sheetname);
		object = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int row = 0; row < sheet.getLastRowNum(); row++) {
			for (int column = 0; column < sheet.getRow(0).getLastCellNum(); column++) {
				object[row][column] = sheet.getRow(row + 1).getCell(column).toString();
			}
		}
		return object;
	}
}
