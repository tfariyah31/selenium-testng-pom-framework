package Tfar.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Tfar.TestComponents.BaseTest;

public class ExtentReportManager extends BaseTest implements ITestListener{
	
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent; //populate common info in report
	public ExtentTest test;  // creates test case entries and updates status of test methods in report
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test Report " + timestamp +".html";
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//" + repName);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Smoke Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
	
		String os = testContext.getCurrentXmlTest().getParameter("os");  //parameter values from the testng.xml
		extent.setSystemInfo("Browser", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
			
		}
		
		logger.info("***** Extent Report Started *****");
	    
	}
	
	
	public void onTestStart(ITestResult result){
		
		String className = result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1);
		String methodName = result.getMethod().getMethodName();
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
		logger.info("***** Started Test *****" + className + "->" + methodName + result.getName() + " on Thread " + Thread.currentThread().getName());
	    
	}
	
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().assignCategory(result.getMethod().getGroups());  //display groups in report
		extentTest.get().log(Status.PASS, "Test Case Passed: " + result.getName());
		
		logger.info("Test Case Passed: " + result.getName() + " on Thread " + Thread.currentThread().getName());
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		String className = result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1);
		String methodName = result.getMethod().getMethodName();
		
		logger.info("***** Test Case Failed *****" + className + "->" + methodName + " on Thread " + Thread.currentThread().getName() );
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}catch(Exception ed){
			ed.printStackTrace();
		}
		
		extentTest.get().assignCategory(result.getMethod().getGroups());  //display groups in report
		extentTest.get().log(Status.FAIL, "Test Case Failed: " + result.getName());
		extentTest.get().log(Status.INFO, "Test Case Fail cause: " + result.getThrowable().getMessage());
		
		logger.info("Test Case Failed: " + result.getName() + " on Thread " + Thread.currentThread().getName());
		
		//capture screenshot
		try {
			String imgPath = new BaseTest().captureScreen(result.getName(),driver);
			extentTest.get().addScreenCaptureFromPath(imgPath);
			
		}catch(IOException io) {
			io.printStackTrace();
			logger.error(io.getMessage());
		}
	    
	}

	public void onTestSkipped(ITestResult result) {
		
		extentTest.get().log(Status.SKIP, "Test Case Skipped: " + result.getName());
		logger.info("Test Case Skipped: " + result.getName() + " on Thread " + Thread.currentThread().getName());
	}
	
	public void onFinish(ITestContext context) {
		
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "//reports//" + repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		logger.info("***** Extent Report Completed *****");
	}
	

	

}
