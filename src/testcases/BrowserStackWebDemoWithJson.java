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

public class BrowserStackWebDemoWithJson extends BaseTest
{
	String Browser;
	String BrowserVersion;
	String os;
	String PlatformVersion;
	String Resolution;
	
	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException
	{
		parser = new JSONParser();
		Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\util\\web.json"));
		jsonArray = (JSONArray) obj;
		
		for(int i=0;i<jsonArray.size();i++)
		{
		    j=(JSONObject)jsonArray.get(i);
		    Browser = (String) j.get("browser");
		    BrowserVersion = (String) j.get("browser_version");
		    os = (String) j.get("os");
		    PlatformVersion = (String) j.get("os_version");
		    Resolution = (String) j.get("resolution");
		    
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
		    driver.get("http://demo.automationtesting.in/Register.html");
		}
	}
	
	@Test
	public void verifyUserRegistration() throws InterruptedException
	{
		Register register = PageFactory.initElements(driver, Register.class);
		test=extent.createTest("Verify User Registration - "+"("+os+" - "+PlatformVersion+" - "+Browser+" - "+BrowserVersion+" - "+Resolution+")");
		register.fillRegistrationform();
		test.log(Status.INFO, "User Registration Test Case has been Verified");
		test.log(Status.PASS, "verifyUserRegistrationForm Success");
	}
	
	@Test
	public void verifyPhotoUpload()
	{
		Register register = PageFactory.initElements(driver, Register.class);
		test=extent.createTest("verifyPhotoUpload - "+"("+os+" - "+PlatformVersion+" - "+Browser+" - "+BrowserVersion+" - "+Resolution+")");
		register.photoUpload();
		test.log(Status.INFO, "Photo Uploaded Successfully");
		test.log(Status.PASS, "verifyPhotoUpload Sucess");
	}
}
