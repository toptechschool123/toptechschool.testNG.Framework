package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.BaseClass;
import pageObjects.RegisterAccountPageObjects;
import utils.DriverUtility;
import utils.ExcelUtility;

public class RegisterAccountTests extends BaseClass {
	
	RegisterAccountPageObjects registerObj; 

// testng

@BeforeTest
public void beforeTest() {
	
	BaseClass.initializeDriver();
	logger.info("browser is opening");
	
}


@Test(dataProvider = "getTestData")
public void registerAccount(String name, String city, String address, String email, String password) {
	
	
	RegisterAccountPageObjects registerObj = new RegisterAccountPageObjects(); 
	
	registerObj.clickOnMyAccount();
	logger.info("clicking my account");
	DriverUtility.screenShot();
	registerObj.enterName(name);
	logger.info("entering myName");
	registerObj.enterAddress(address);
	logger.info("entering my address");
	registerObj.enterEmail(email);
	logger.info("entering my email");
	registerObj.enterPassword(password);
	logger.info("intering the password");
	registerObj.clickRegisterBttn();
	logger.info("clicking the register button");
	DriverUtility.screenShot();
}

@DataProvider()
public Object[][] getTestData(){
	Object[][] data = ExcelUtility.getExcelData("info");
	
	return data;
}

@AfterTest
public void GracefulTearDown() {
	
	driver.close();
	logger.info("closing the browser");
}
}