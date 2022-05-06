package utilities;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LinksUtilityClass {
	
	private RemoteWebDriver driver;
	@FindBys({@FindBy(xpath="//*")}) private List<WebElement> allElements;
	private List<WebElement> links;
	
	public LinksUtilityClass(RemoteWebDriver driver)
	{
		AjaxElementLocatorFactory adriver=new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(adriver, this);
		this.driver=driver;
	}
	public List<WebElement> getAllHyperLinks()
	{
		links=new ArrayList<WebElement>();
		try
		{
		for(WebElement element:allElements)
		{
			if(element.getAttribute("href")!=null)
			{
				String x=element.getAttribute("href");
				//filter "tel:","mailTo:","java script:",...etc(remove other than hyper links)
				if(x.startsWith("https")|| x.startsWith("http"))
				{
					links.add(element);
				}
			}
		}
		
	}
		catch(Exception ex)
		{
			
		}
	
	return(links);
	}
	
	public int getLinksCount()
	{
		if(links==null)
		{
			getAllHyperLinks();//collect all links
		}
		return(links.size());
	}
	
	public List<String> getBrokenLinks() throws Exception
	{
		if(links==null)
		{
			getAllHyperLinks();//collect all links
		}
		List<String> results=new ArrayList<String>();
		for(WebElement link:links)
		{
			String address=link.getAttribute("href");
			try
			{
				URL u=new URL(address);
				HttpURLConnection con=(HttpURLConnection) u.openConnection();
				con.connect();
				int sc=con.getResponseCode();
				String msg=con.getResponseMessage();
				if(sc!=200)
				{
					results.add(address+"is broken due to"+sc+"and"+msg);
				}
			}
			catch(Exception ex)
			{
				results.add(address+"is not working due to"+ex.getMessage());
			}
		}
		return(results);
	}
	
	
	
	}


