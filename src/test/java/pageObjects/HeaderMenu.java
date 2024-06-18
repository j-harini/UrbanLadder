package pageObjects;

import java.time.Duration;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.BaseClass;

public class HeaderMenu
{
	WebDriver driver;
	Actions hover;
	String homePageURL;
	String url;
	WebDriverWait wait;
	Logger logger;

	
	//Constructor
	public HeaderMenu(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		BaseClass baseclass=new BaseClass();
		logger=baseclass.logElement();
	}
	
	//LOCATORS	
	//living menu
	@FindBy(xpath="//span[contains(text(),'Living') and @class=\"topnav_itemname\"]") 
	WebElement Living_menu;
	
	//Categories in living menu
	@FindBy(xpath="//li[@class=\"topnav_item livingunit\"]/descendant::li[@class=\"sublist_item\"]")
	List <WebElement> categories;
	By topics = By.xpath("./div");
	By items = By.xpath("./ul/li");
	
	
	//ACTION METHOD
	
	//Navigates Back to HomePage
	public void navigatingBack()
	{
		logger.info("Navigates Back to HomePage");
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		
		url = driver.getCurrentUrl();
		if(url.equals(homePageURL))
		{
			System.out.println("Navigated Back to Homepage");
		}
		else 
		{
			driver.navigate().back();
			System.out.println("Navigated Back to Homepage");
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	//Hovers over Living Menu
	public void hoverLivingMenu()
	{
		logger.info("Hovers Living Menu");
		BaseClass.highlightElement(Living_menu);
		hover = new Actions(driver);
		hover.moveToElement(Living_menu).build().perform();
		System.out.println();
		System.out.println("-> Hovering over LIVING to collect all menu items");
		System.out.println();	
	}
	
	//Displays the SubMenu Items
	public void displayMenuItems()
	{
		logger.info("Collects all SubMenu items in a List and Displaying the same");
		for(WebElement category:categories)
		{
			WebElement name = category.findElement(topics);
			BaseClass.highlightElement(name);
			System.out.println("Categories:"+name.getText());
			List <WebElement> titles = category.findElements(items);
			for(WebElement title : titles)
			{
				BaseClass.highlightElement(title);
				System.out.println("->"+title.getText());
			}
			
		}
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
	}

}
