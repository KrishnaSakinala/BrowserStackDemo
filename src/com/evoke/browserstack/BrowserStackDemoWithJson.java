package com.evoke.browserstack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BrowserStackDemoWithJson 
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
	
	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/BrowserStackReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
		
		parser = new JSONParser();
		Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\util\\browsers.json"));
		jsonArray = (JSONArray) obj;
		
		for(int i=0;i<jsonArray.size();i++)
		{
		    /*j=(JSONObject)jsonArray.get(i);
		    String Browser = (String) j.get("browser");
		    String BrowserVersion = (String) j.get("browser_version");
		    String Platform = (String) j.get("os");
		    String PlatformVersion = (String) j.get("os_version");
		    String Resolution = (String) j.get("resolution");
		    System.out.println("Run no."+(i+1));
		    DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", Browser);
		    caps.setCapability("browser_version", BrowserVersion);
		    caps.setCapability("os", Platform);
		    caps.setCapability("os_version", PlatformVersion);
		    caps.setCapability("resolution", Resolution);
		    caps.setCapability("browserstack.debug", "true");
		    caps.setCapability("build","BrowserCompatibility_SequentialRuns");*/
			
			j=(JSONObject)jsonArray.get(i);
		    String Browser = (String) j.get("browser");
		    String BrowserVersion = (String) j.get("browser_version");
		    String os = (String) j.get("os");
		    String PlatformVersion = (String) j.get("os_version");
		    String Resolution = (String) j.get("resolution");
		    
		    String BrowserName = (String) j.get("browserName");
		    String Platform = (String) j.get("platform");
		    String Device = (String) j.get("device");
		    
		    System.out.println("Run no."+(i+1));
		    DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", Browser);
		    caps.setCapability("browser_version", BrowserVersion);
		    caps.setCapability("os", os);
		    caps.setCapability("os_version", PlatformVersion);
		    caps.setCapability("resolution", Resolution);
		    
		    caps.setCapability("browserName", BrowserName);
		    caps.setCapability("platform", Platform);
		    caps.setCapability("device", Device);
		    
		    caps.setCapability("browserstack.debug", "true");
		    caps.setCapability("build","BrowserCompatibility_SequentialRuns");
		    
		    driver = new RemoteWebDriver(new URL(URL), caps);
		    driver.get("http://www.evoketechnologies.com/");
		}
	}
	
	@Test
	public void titleVerification()
	{
		test = extent.createTest("titleVerification");
		String actualTitle = "IT Services | Software Consulting & Outsourcing";
		String expectedTitle = driver.getTitle();
		test.log(Status.INFO,"Getting the Website Title :"+expectedTitle);
		Assert.assertEquals(expectedTitle, actualTitle);
	}
	
	@Test
	public void urlVerification()
	{
		test = extent.createTest("urlVerification");
		String actualUrl = "http://www.evoketechnologies.com/";
		String expectedUrl = driver.getCurrentUrl();
		test.log(Status.INFO,"Getting the Website Url :"+expectedUrl);
		Assert.assertEquals(expectedUrl, actualUrl);
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
