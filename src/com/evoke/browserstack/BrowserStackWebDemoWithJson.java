package com.evoke.browserstack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class BrowserStackWebDemoWithJson extends BaseTest
{
	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException
	{
		parser = new JSONParser();
		Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\util\\web.json"));
		jsonArray = (JSONArray) obj;
		
		for(int i=0;i<jsonArray.size();i++)
		{
		    j=(JSONObject)jsonArray.get(i);
		    String Browser = (String) j.get("browser");
		    String BrowserVersion = (String) j.get("browser_version");
		    String os = (String) j.get("os");
		    String PlatformVersion = (String) j.get("os_version");
		    String Resolution = (String) j.get("resolution");
		    
		    System.out.println("Run no."+(i+1));
		    DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", Browser);
		    caps.setCapability("browser_version", BrowserVersion);
		    caps.setCapability("os", os);
		    caps.setCapability("os_version", PlatformVersion);
		    caps.setCapability("resolution", Resolution);
		    
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
}
