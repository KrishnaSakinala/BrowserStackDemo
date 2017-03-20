package com.evoke.browserstack;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrowserStackMobileDemo
{
	public static final String USERNAME = "krishna245";
	public static final String AUTOMATE_KEY = "rzsP7d6ARddxvbSdAp3x";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	WebDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName","iPhone");
		caps.setPlatform(Platform.MAC);
		caps.setCapability("device", "iPhone 5");
		caps.setCapability("browserstack.debug", "true");

		driver = new RemoteWebDriver(new URL(URL), caps);
		
		driver.get("http://www.evoketechnologies.com/");
	}
	
	@Test
	public void titleVerification()
	{
		String actualTitle = "IT Services | Software Consulting & Outsourcing";
		String expectedTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
	}
	
	@Test
	public void urlVerification()
	{
		String actualUrl = "http://www.evoketechnologies.com/";
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}

