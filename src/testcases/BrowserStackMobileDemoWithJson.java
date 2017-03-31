package testcases;

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
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.Register;
import base.BaseTest;

import com.aventstack.extentreports.Status;

public class BrowserStackMobileDemoWithJson extends BaseTest
{
	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException
	{
		parser = new JSONParser();
		Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\util\\mobile.json"));
		jsonArray = (JSONArray) obj;
		
		for(int i=0;i<jsonArray.size();i++)
		{
		    j=(JSONObject)jsonArray.get(i);
		    		    
		    String BrowserName = (String) j.get("browserName");
		    String Platform = (String) j.get("platform");
		    String Device = (String) j.get("device");
		    
		    System.out.println("Run no."+(i+1));
		    DesiredCapabilities caps = new DesiredCapabilities();
		    		    
		    caps.setCapability("browserName", BrowserName);
		    caps.setCapability("platform", Platform);
		    caps.setCapability("device", Device);
		    
		    caps.setCapability("browserstack.debug", "true");
		    caps.setCapability("build","BrowserCompatibility_SequentialRuns");
		    
		    driver = new RemoteWebDriver(new URL(URL), caps);
		    driver.get("http://demo.automationtesting.in/Register.html");
		}
	}
	
	@Test
	public void verifyUserRegistration() throws InterruptedException
	{
		Register register = PageFactory.initElements(driver, Register.class);
		test=extent.createTest("Verify User Registration");
		register.fillRegistrationform();
		test.log(Status.INFO, "User Registration Test Case has been Verified");
		test.log(Status.PASS, "verifyUserRegistrationForm Success");
	}
	
	@Test
	public void verifyPhotoUpload()
	{
		Register register = PageFactory.initElements(driver, Register.class);
		test=extent.createTest("verifyPhotoUpload");
		register.photoUpload();
		test.log(Status.INFO, "Photo Uploaded Successfully");
		test.log(Status.PASS, "verifyPhotoUpload Sucess");
	}
	
	
}

