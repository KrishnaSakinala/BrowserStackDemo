package pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import config.ConstantsClass;
import util.CommonMethods;

public class Register
{
	public WebDriver driver;
	
	public Register(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@ng-model='FirstName']")
	public WebElement firstName;

	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@ng-model='LastName']")
	public WebElement lastName;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//textarea[@ng-model='Adress']")
	public WebElement adress;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@type='email']")
	public WebElement email;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@type='tel']")
	public WebElement phone;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@type='radio']")
	public WebElement gender;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@type='checkbox']")
	public WebElement hobbies;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//select[@id='Skills']")
	public WebElement skills;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//select[@id='countries']")
	public WebElement country;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//select[@id='yearbox']")
	public WebElement yearBox;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//select[@placeholder='Month']")
	public WebElement monthBox;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//select[@id='daybox']")
	public WebElement dayBox;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@id='firstpassword']")
	public WebElement password;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@id='secondpassword']")
	public WebElement confirmPassword;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//button[contains(text(),'Submit')]")
	public WebElement submitButton;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//input[@id='imagesrc']")
	public WebElement browseButton;
	
	
	public void clickSubmitWithEmptyFields()
	{
		CommonMethods.click(submitButton);
		
	}
	
	public void fillRegistrationform() throws InterruptedException
	{
		firstName.sendKeys(util.Data.firstName);
		lastName.sendKeys(util.Data.lastName);
		adress.sendKeys(util.Data.adress);
		Random rand= new Random();
		int number=rand.nextInt(10000);
		String emailId = "email"+number+"@gmail.com";
		email.sendKeys(emailId);
		String phoneNumber = Long.toString((long)(Math.random()*100000 + 9933300000L));
		phone.sendKeys(phoneNumber);
		CommonMethods.click(gender);
		CommonMethods.click(hobbies);
		CommonMethods.select(skills, 1);
		CommonMethods.select(country, 2);
		CommonMethods.select(yearBox, 2);
		CommonMethods.select(monthBox, 2);
		CommonMethods.select(dayBox, 2);
		password.sendKeys(util.Data.passowrd);
		confirmPassword.sendKeys(util.Data.confrimPassword);
		submitButton.click();
		Thread.sleep(7000);
				
	}
	
	public void photoUpload()
	{
		browseButton.sendKeys(ConstantsClass.imageUrl);
	}
}


