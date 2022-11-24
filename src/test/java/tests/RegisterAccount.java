package tests;

import core.BaseClass;
import pageObjects.RegisterAccountPageObjects;

public class RegisterAccount extends BaseClass {
	
	RegisterAccountPageObjects registerObj; 



	
public void beforeTest() {
	
	BaseClass.initializeDriver();
	
	
}
public void registerAccount() {
	registerObj = new RegisterAccountPageObjects(); 
	
}
}