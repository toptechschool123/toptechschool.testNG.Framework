package tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import core.BaseClass;
import pageObjects.LoginPageObjects;

public class LoginSteps_Simple_testngAnno extends BaseClass {
	LoginPageObjects loginObj;

	@BeforeTest
	public void user_is_on_test_environment_page() {
		BaseClass.initializeDriver();
		logger.info("first step");
	}

	@Test(priority = 1, description = "loginTestPage , priority one")
	public void loginTestPage() {
		loginObj = new LoginPageObjects();

		loginObj.clickMyAccount();
		loginObj.enterUserName(getUserName());
		loginObj.enterPassword(getPassword());
		loginObj.clickLogin();
		
		String Actualtitle = BaseClass.driver.getTitle();
		System.out.println(Actualtitle);
		
		String expectedTtile = "My account � Top Tech School";
		Assert.assertEquals(Actualtitle, expectedTtile);   // issues with assertion of the title. assertion fails. title does not match. 
		System.out.println("I am at priority one ");

	}

	@Test(priority = 2, description = "schoolAccount, priority two")
	public void schoolAccount() {
		System.out.println("I am at priority two");
	}

	@Test(priority = 3, description = "homeAccount, priority three")
	public void homeAccount() {
		System.out.println("I am at priority 3");
	}

	@AfterTest
	public void gracefulTearDown() {

		BaseClass.tearDown();

	}

}
