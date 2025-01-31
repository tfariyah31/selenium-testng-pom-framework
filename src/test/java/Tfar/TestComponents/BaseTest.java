package Tfar.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BaseTest {
	
	public WebDriver driver;
	protected static final Logger logger = LogManager.getLogger(BaseTest.class); //This variable can be seen by this class, classes in the same package and classes which extend me and immutable
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream pfile = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//GlobalParameters.properties");
		prop.load(pfile);															
		
		String browserName = prop.getProperty("browser");
				
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
			}
		else {
			driver = new FirefoxDriver();
			}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
			
	}
	
	@BeforeSuite
	public void setupSuite(){
		
		logger.info("Starting Execution for Test Suites..... ");
	}
	
	@BeforeMethod(alwaysRun=true)
	public void launchApplication() throws IOException{
		
		driver = initializeDriver();
		driver.get("https://www.saucedemo.com/");
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
		
	public List<HashMap<String,String>> getJsonDataToMap() throws IOException 
	{
			File jsonFile = new File(System.getProperty("user.dir")+"//src//test//java//Tfar//data//jsonDataFile.json");
			ObjectMapper objmap = new ObjectMapper();
			List<HashMap<String, String>> dataList = objmap.readValue(jsonFile, new TypeReference<List<HashMap<String, String>>>() {});
			
			return dataList;
			
	}
    
	public String captureScreen(String tname, WebDriver driver) throws IOException{
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeSceenShot = (TakesScreenshot) driver;
		File sourceFile = takeSceenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" +timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
}