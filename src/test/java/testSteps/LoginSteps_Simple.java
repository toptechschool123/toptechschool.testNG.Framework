package testSteps;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import core.BaseClass;

import pageObjects.LoginPageObjects;

public class LoginSteps_Simple extends BaseClass {
	LoginPageObjects loginObj;

 @BeforeMethod
	public void user_is_on_test_environment_page() {
		BaseClass.initializeDriver();
		logger.info("first step");
	}

 @Test
 public void loginTestPage() {
	 loginObj = new LoginPageObjects();
	 
	 loginObj.clickMyAccount();
	 loginObj.enterUserName(getUserName());
	 loginObj.enterPassword(getPassword());
	 loginObj.clickLogin();
 }
 
 @AfterMethod
 public  void gracefulTearDown() {
	 
	 BaseClass.tearDown();
	 
 }
	
}
