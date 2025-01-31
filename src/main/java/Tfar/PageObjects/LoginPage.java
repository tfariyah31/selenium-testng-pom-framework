package Tfar.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Tfar.AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent{

	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="user-name")
	WebElement userName;
	
	@FindBy(id="password")
	WebElement passwordEle;
	
	@FindBy(id="login-button")
	WebElement submit;
	
	@FindBy(xpath="//span[@class='title']")
	WebElement successMessage;
	
	@FindBy(css="h3[data-test='error']")
	WebElement errorMessage;

	public ProductsPage loginToApplication(String user_name,String password)
	{
		userName.sendKeys(user_name);
		passwordEle.sendKeys(password);
		submit.click();
		ProductsPage productPage = new ProductsPage(driver);
		return productPage;
			
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
