package gluecode;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GooglePage;

public class StepDef1 
{
	public Shared sh;
	//Dependency injection via constructor
	public StepDef1(Shared x)
	{ 
		sh=x;
	}
	//Step definitions
	@Given("open {string} browser")
	public void method1(String bn) 
	{
		if(bn.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			sh.driver=new ChromeDriver();
		}
		else if(bn.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			sh.driver=new FirefoxDriver();
		}	
		else
		{
			WebDriverManager.edgedriver().setup();
			sh.driver=new EdgeDriver();
		}
		sh.obj=new GooglePage(sh.driver);
	}
	
	@When("i launch {string} site")
	public void method2(String url) throws Exception
	{
		sh.driver.get(url);
		Thread.sleep(5000);
	}
	
	@Then("title should be {string}")
	public void method3(String expected) throws Exception
	{
		if(sh.obj.verifyTitle(sh.driver, expected))
		{
			sh.s.log("Test passed");
		}
		else
		{
			sh.s.log("Test failed");
		}
	}
}
