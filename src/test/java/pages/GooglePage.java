package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage
{
	//Locators of element as properties in page class
	@FindBy(name="q")
	private WebElement serachbox;
	
	//Constructor method
	public GooglePage(RemoteWebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Operations and observations methods
	public void fillSearchboxAndClickEnter(String data) throws Exception
	{
		serachbox.clear();
		Thread.sleep(3000);
		serachbox.sendKeys(data, Keys.ENTER);
		Thread.sleep(3000);
	}
	
	public boolean verifyTitle(RemoteWebDriver driver, String expected)
	{
		if(driver.getTitle().contains(expected))
		{
			return(true);
		}
		else
		{
			return(false);
		}
	}
}
