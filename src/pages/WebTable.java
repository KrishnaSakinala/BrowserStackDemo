package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import util.CommonMethods;

public class WebTable 
{
	public WebDriver driver;
	public WebTable(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//div[@class='ui-grid-canvas']/div/div/div[5]")
	public List<WebElement> phoneColumn;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//div[@class='ui-grid-canvas']/div[1]/div/div[6]/user-click-select/div[1]/button/i")
	public WebElement editButton;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//div[@class='ui-grid-canvas']/div[1]/div/div[1]")
	public WebElement emailField;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//div[@class='ui-grid-canvas']/div[1]/div/div[6]/user-click-select/div[2]/save-click/button")
	public WebElement saveButton;
	
	@CacheLookup
	@FindBy(how=How.XPATH, using="//div[@ng-if='!Emailvalid']")
	public WebElement errorText;
	
	public void registeredUserDisplauyedInWebTable()
	{
		List<String> allValues = new ArrayList<String> () ;
		for(WebElement values : phoneColumn)
		{
			String phoneNumber = values.getText();
			allValues.add(phoneNumber);		
		}
		Assert.assertTrue(allValues.contains("9010451122"));	
	}
	
	public void editFeildValueInWebTable() throws Exception
	{
		Actions act = new Actions(driver);
		act.doubleClick(editButton).build().perform();
		CommonMethods.waitForElement(emailField, 10);
		emailField.sendKeys("abc");
		saveButton.click();
		Thread.sleep(1000);
		Assert.assertTrue(errorText.getText().equals("Email is Invalid"));
	}
}


