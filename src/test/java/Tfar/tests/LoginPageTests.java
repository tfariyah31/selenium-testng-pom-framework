package Tfar.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Tfar.PageObjects.LoginPage;
import Tfar.TestComponents.BaseTest;
import Tfar.utilities.Retry;


public class LoginPageTests extends BaseTest{
	 
	@Test(priority = 1)
	public void LoginTest() throws IOException {
		
		//Test to verify successful login with valid credentials.
		LoginPage lp = new LoginPage(driver);
		lp.loginToApplication("standard_user","secret_sauce");
	
		//Assertion
		try {
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
			}catch(AssertionError ae) {
				ae.printStackTrace();
				logger.error(ae.getMessage());
				Assert.fail();
			}
	}
	
	@Test(priority = 2)
	public void LoginTestInValidCredentials() throws IOException {
		
		//Test to verify login with invalid credentials
		LoginPage lp = new LoginPage(driver);
		lp.loginToApplication("invalid_user","invalid_sauce");
		
		//Assertion
		try {
			Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", lp.getErrorMessage());
			}catch(AssertionError ae) {
			ae.printStackTrace();
			logger.error(ae.getMessage());
			Assert.fail();
		}		
	}
	
	@Test(priority = 3, retryAnalyzer = Retry.class)
	public void LoginTestLockedOutUser() throws IOException {
		
		//Test to verify login with locked out user	
		LoginPage lp = new LoginPage(driver);
		lp.loginToApplication("locked_out_user","secret_sauce");
		
		//Assertion
		try {
			Assert.assertEquals(lp.getErrorMessage(),"Epic sadface: Sorry, this user has been locked out.");
		}catch(AssertionError ae) {
			ae.printStackTrace();
			logger.error(ae.getMessage());
			Assert.fail();
		}
		
	}
		
}
