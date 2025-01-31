package Tfar.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Tfar.PageObjects.CartPage;
import Tfar.PageObjects.LoginPage;
import Tfar.PageObjects.ProductsPage;
import Tfar.TestComponents.BaseTest;

public class CartPageTests extends BaseTest{

    @Test(dataProvider = "jsonDataProvider", priority = 1)
	public void testJsonData(HashMap<String, String> data) throws InterruptedException{
		
    	//Verifies that the cart page displays the correct product names after adding to cart.
    	//This test uses data-driven testing, where the test data is provided by the {@link #getData()} method.
        LoginPage lp = new LoginPage(driver);
        String user_name = data.get("user_name");
		String pass = data.get("password");
        String productName = data.get("product");
        ProductsPage productsPage = lp.loginToApplication(user_name,pass);
        productsPage.addToCart(productName);
        CartPage cartPage = productsPage.goToCart();
        String buttonText = cartPage.VerifyCartDisplay();
        
        //Assertion
        try {
			Assert.assertEquals(buttonText, productName);
		}catch(AssertionError ae) {
			ae.printStackTrace();
			logger.error(ae.getMessage());
			Assert.fail();}
		
	}
	
	@DataProvider(name = "jsonDataProvider")
	public Object[][] getData() throws IOException{
		
		//Provides data for the {@link #testJsonData(HashMap)} test in the form of an array of objects, 
	    //where each object is a HashMap containing the data for one test iteration.
		List<HashMap<String, String>> dataList = getJsonDataToMap();
		Object[][] testData = new Object[dataList.size()][1];
		for (int i = 0; i < dataList.size();i++) {
			testData[i][0] = dataList.get(i);
		}
		return testData;
		
	}

}
