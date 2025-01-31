package Tfar.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Tfar.AbstractComponent.AbstractComponent;

public class ProductsPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(id= "react-burger-menu-btn")
	WebElement menuButton;
	
	@FindBy(id= "logout_sidebar_link")
	WebElement logoutLink;
	
	@FindBy(css= "div[class='inventory_item_name ']")
	List<WebElement> productsList;
	
	@FindBy(css="#add-to-cart-sauce-labs-backpack")
	WebElement backpackAddToCart;
    @FindBy(css = "#remove-sauce-labs-backpack")
    WebElement removebackpack;

	@FindBy(css="#add-to-cart-sauce-labs-bike-light")
	WebElement bikeLightAddToCart;
	@FindBy( css = "#remove-sauce-labs-bike-light")
    WebElement removeBikeLight;
	
	@FindBy(css="#add-to-cart-sauce-labs-bolt-t-shirt")
	WebElement tShirtAddToCart;
	@FindBy(css="#remove-sauce-labs-bolt-t-shirt")
	WebElement removetShirt;
	
	@FindBy(css="#add-to-cart-sauce-labs-fleece-jacket")
	WebElement jacketAddToCart;
	@FindBy(css="#remove-sauce-labs-fleece-jacket")
	WebElement removetjacket;
	
	@FindBy(css=".shopping_cart_link")
	WebElement cartIcon;
	
	public void openMenu() {
		
		menuButton.click();
	}
	
	public void clickLogoutLink() {
		
		logoutLink.click();
	}
	
	
	public int getProductCount() {
		
		return productsList.size();
	}
	
	public boolean isProductDisplayed(String productName) {
		
		for(WebElement product : productsList) {
			if(product.getText().equalsIgnoreCase(productName))	{
				return true;
			}
		}
		return false;
	}

	public List<String> getProductNames() {
		
		List <String> productNames = new ArrayList<String>();

		for(WebElement product : productsList) {
			productNames.add(product.getText());
		}
		return productNames;
	}
	public String addToCart(String productName) throws InterruptedException
	{
		
		String buttonText = null;
		if (productName.equals("Sauce Labs Backpack")) {
        	backpackAddToCart.click();
        	waitForWebElementToAppear(removebackpack);
    		buttonText = removebackpack.getText();
        } else if (productName.equals("Sauce Labs Bike Light")) {
        	bikeLightAddToCart.click();
        	waitForWebElementToAppear(removeBikeLight);
    		buttonText = removeBikeLight.getText();
        } else if (productName.equals("Sauce Labs Bolt T-Shirt")) {
        	tShirtAddToCart.click();
        	waitForWebElementToAppear(removetShirt);
    		buttonText = removetShirt.getText();
        }else if (productName.equals("Sauce Labs Fleece Jacket")) {
        	jacketAddToCart.click();  
        	waitForWebElementToAppear(removetjacket);
    		buttonText = removetjacket.getText();
        } 
		return buttonText;
	}

	public CartPage goToCart() {
		cartIcon.click();
		return new CartPage(driver);
	}
}
	
	
	
	