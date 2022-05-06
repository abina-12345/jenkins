package gluecode;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.GooglePage;
import utilities.LinksUtilityClass;

public class Shared
{
	//declare common objects
	public RemoteWebDriver driver;
	public GooglePage obj;
	public Scenario s; //to customize results file
	public LinksUtilityClass lu;
	@Before
	public void method(Scenario x)
	{
		s=x; //define scenario object to a scenario, which is coming into execution for next , or upcoming scenario
	}
}
 