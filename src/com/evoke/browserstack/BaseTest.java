package com.evoke.browserstack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest 
{
	public static JSONParser parser;
	public static JSONArray jsonArray;
	public static final String USERNAME = "krishna245"; 
	public static final String AUTOMATE_KEY = "rzsP7d6ARddxvbSdAp3x"; 
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
	public static JSONObject j;
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;
    
    @BeforeSuite
    public void init()
    {
    	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/BrowserStackReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
    
    @AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
	
	@AfterClass
	public void tearDown()
	{
		extent.flush();
    	driver.quit();
	}
}
