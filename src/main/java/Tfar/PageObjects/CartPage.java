package Tfar.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Tfar.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{
    
	WebDriver driver;

    public CartPage(WebDriver driver) {

        super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
    }

    @FindBy(css=".inventory_item_name")
	WebElement productName;

    @FindBy(css="#checkout")
	WebElement checkout;

    public String VerifyCartDisplay() {
        waitForWebElementToAppear(productName);
        String product = productName.getText();
        return product;
    }

    
}

