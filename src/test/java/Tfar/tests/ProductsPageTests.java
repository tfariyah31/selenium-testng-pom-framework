package Tfar.tests;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Tfar.PageObjects.LoginPage;
import Tfar.PageObjects.ProductsPage;

import Tfar.TestComponents.BaseTest;

public class ProductsPageTests extends BaseTest {
	
	@Test(priority = 1)
	public void LogoutTest() throws IOException, InterruptedException { 
		
		//Verifies that the logout functionality works correctly from the products page.
		LoginPage lp = new LoginPage(driver);
		ProductsPage productsPage = lp.loginToApplication("standard_user","secret_sauce");
		productsPage.openMenu();
		productsPage.clickLogoutLink();
		
		//Assertion
		try {
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
		}catch(AssertionError ae) {
			ae.printStackTrace();
			logger.error(ae.getMessage());
			Assert.fail();
		}
		
	}
	
	@Test(priority = 2)
	public void ProductDisplay() throws IOException, InterruptedException { 
		
		//Verifies that the product display is correct on the products page.
		LoginPage lp = new LoginPage(driver);
		ProductsPage productsPage = lp.loginToApplication("standard_user","secret_sauce");
		int productCount = productsPage.getProductCount();
		Assert.assertTrue(productCount > 0,"No products found");
		
		String expectedProductNames[] = {"Sauce Labs Backpack","Sauce Labs Bike Light","Sauce Labs Bolt T-Shirt","Sauce Labs Fleece Jacket","Sauce Labs Onesie","Test.allTheThings() T-Shirt (Red)"};
		List<String> productsNamesFound = productsPage.getProductNames();
		for(int i=0;i<productCount;i++) {
			Assert.assertEquals(productsNamesFound.get(i), expectedProductNames[i]);
		}
		
	}
	
	@Test(priority = 3)
	public void addToCart() throws InterruptedException {
		
		//Verifies that the "Add to Cart" functionality works correctly.
		String productName = "Sauce Labs Backpack";
		LoginPage lp = new LoginPage(driver);
		ProductsPage productsPage = lp.loginToApplication("standard_user","secret_sauce");
		String buttonText = productsPage.addToCart(productName);
		
		//Assertion
		try {
			Assert.assertEquals(buttonText, "Remove");
		}catch(AssertionError ae) {
			ae.printStackTrace();
			logger.error(ae.getMessage());
			Assert.fail();
		}
		
	}
}
