package factory;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
 
public class BaseClass 
{
	static WebDriver driver;
	static Properties p;
	Logger logger;
	static String homePageURL;
	
	//Initializes the Browser
	public static WebDriver initializeBrowser() throws IOException
	{
		//Gets Data from the Property File
		p=getProperties();
		String executionEnvironment = p.getProperty("exceution_environment");
		String browser = p.getProperty("browser").toLowerCase();
		String os = p.getProperty("os").toLowerCase();
		String link = p.getProperty("appURL");
		
		//Checks Operating System
		if(executionEnvironment.equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//os
			switch(os) 
			{
			case "windows":
				capabilities.setPlatform(Platform.WINDOWS);
				break;
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			case "linux":
				capabilities.setPlatform(Platform.LINUX);
				break;
			default:
				System.out.println("No Matching OS");
				return null;
			}
			//Checks the Browser
			switch(browser)
			{
			case "Chrome":
				capabilities.setBrowserName("Chrome");
				break;
			case "Edge":
				capabilities.setBrowserName("Edge");
				break;
			default:
				System.out.println("No Matchingg Browser");
				return null;
			}
			//For Grid
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		//For Local Execution
		else if(executionEnvironment.equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase())
			{
			case "chrome":
				driver=new ChromeDriver();
				driver.get(link);
				System.out.println("OPENING CHROME BROWSER");

				break;
			case "Edge":
				driver = new EdgeDriver();
				driver.get(link);
				break;
			default:
				System.out.println("No Matching Browser");
				driver= null;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		homePageURL = driver.getCurrentUrl();
		System.out.println("Navigated to :"+ homePageURL);
		return driver;
	}
	
	//Returns Driver
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	//Closes all the Browser
	public static void tearDown() 
	{
		System.out.println("CLOSING BROWSER");
		driver.quit();
	}
 
	//Reads the Property File
	public static  Properties getProperties() throws IOException 
	{
		FileReader file = new FileReader("C:\\Users\\2320144\\eclipse-workspace\\cucumberproject\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		return p;
	}
	
	//To Highlight an Element
	public static void highlightElement(WebElement element)
	{
		JavascriptExecutor jExecutor=(JavascriptExecutor)BaseClass.getDriver();
		jExecutor.executeScript("arguments[0].style.border='3px solid #e76e3c'", element);
	}
	
	//To Highlight a TextBox
	public static void highlightTextBox(WebElement element)
	{
		JavascriptExecutor jExecutor=(JavascriptExecutor)BaseClass.getDriver();
		jExecutor.executeScript("arguments[0].setAttribute('style', 'background-color: #faddd1; border: 3px solid  #e76e3c;')", element);		
	}
	
	//Generates Log File
	public Logger logElement()
	{
		logger = LogManager.getLogger(this.getClass());
		return logger;
	}

}