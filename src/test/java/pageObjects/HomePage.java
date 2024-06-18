package pageObjects;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.BaseClass;

public class HomePage 
{
	
	WebDriver driver;
	Actions hover;
	Actions drag;
	WebDriverWait wait;
	Logger logger;
	
	//constructor
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		BaseClass baseclass=new BaseClass();
		logger=baseclass.logElement();
		
	}
	
	
	//Locators for Elements needed to be accessed in the home page.
	
	//Living Menu
	@FindBy(xpath="//span[contains(text(),'Living') and @class=\"topnav_itemname\"]") 
	WebElement Living_menu;
	
	//Bookshelves SubMenu
	@FindBy(xpath="//li[@class=\"topnav_item livingunit\"]/descendant::span[contains(text(),'Bookshelves')]")
	WebElement Bookshelves_submenu;
	
	//PopUp Box
	@FindBy(xpath="//*[@id='login_dialog']") 
	WebElement alert_popup;
	
	//PopUp Close Button
	@FindBy(xpath="//a[@class=\"close-reveal-modal hide-mobile\"]")
	WebElement alert_close;
	
	//Price DropDown
	@FindBy(xpath="//div[@class=\"gname\"]/parent::li[@data-group=\"price\"]") 
	WebElement price_dropdown;
	
	//Price Slider
	@FindBy (xpath="//div[@class=\"noUi-handle noUi-handle-upper\"]") 
	WebElement price_slider;
	
	//Storage Type DropDown
	@FindBy(xpath="//div[normalize-space()=\'Storage Type\']")
	WebElement storageType_dropdown;
	
	//Open Storage Type
	@FindBy(xpath="//input[@id=\'filters_storage_type_Open\']")
	WebElement open_option;
	
	//Exclude Out of Box CheckBox
	@FindBy(xpath="//input[@id=\'filters_availability_In_Stock_Only\']")
	WebElement outOfStock_checkbox;
	
	//SortBy DropDown
	@FindBy(xpath="//div[@data-group=\"sorting\"]")
	WebElement sortBy_dropdown;

	//High Recommendation Option
	@FindBy(xpath="//li[text()=\"Recommended\"]")
	WebElement recommended_option;
	
	//List of Product Names
	@FindBy(xpath="//span[@class=\"name\"]")
	List<WebElement> productName;
	
	//List of Product Prices
	@FindBy(xpath="//div[@class=\"price-number\"]/span") 
	List <WebElement> productPrice;
	
	
	
	//Action Methods 
	
	//Checks whether Living is Displayed
	public boolean checkLivingMenu()
	{
		 // Waits 10s
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		logger.info("Checking whether Living Menu is Displayed");
		return Living_menu.isDisplayed();
	}
	
	//Hovers over Living Menu
	public void hoverLivingMenu()
	{
		BaseClass.highlightElement(Living_menu);
		hover = new Actions(driver);
		hover.moveToElement(Living_menu).build().perform();
		logger.info("Hovering Living Menu");
	}
	
	//Checks whether Bookshelf is Displayed
	public boolean checkBookshelves()
	{
		logger.info("Checks whether Bookshelf is Displayed");
		return Bookshelves_submenu.isDisplayed();
	}
	
	//Selects Bookshelf SubMenu from Living Menu
	public void selectBookshelves()
	{
		BaseClass.highlightElement(Bookshelves_submenu);
		Bookshelves_submenu.click();
		System.out.println();
		System.out.println("-> Bookshelf is selected from the Living Menu.");
		logger.info("Selects BookShelf from Living Menu");
		
	}
	
	//handles PopUp
	public void handleAlert() throws InterruptedException
	{
		Thread.sleep(5000);
		BaseClass.highlightElement(alert_popup);
		System.out.println("Popup Found:"+alert_popup.isDisplayed());
		if(alert_popup.isDisplayed())
		{
			BaseClass.highlightElement(alert_close);
			alert_close.click();
			System.out.println("-> Popup is closed.");
		}
		logger.info("Handles PopUp");

	}

	//Checks whether Price Filter is Enabled
	public boolean checkPriceFilter()
	{
		logger.info("Checks whether Price Filter is Displayed");
		return price_dropdown.isDisplayed();
	}
	
	//Sets the Price Range
	public void setPrice() throws InterruptedException
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		BaseClass.highlightElement(price_dropdown);
		hover.moveToElement(price_dropdown).build().perform();
		Thread.sleep(7000);
		drag = new Actions(driver);
		int xOffset = -280;
		drag.dragAndDropBy(price_slider,xOffset, 0).build().perform();
		System.out.println("-> Price is set below Rs.15000");
		logger.info("Sets Price Range below Rs.15000");
	}
	
	//Select Open Option from Storage Type
	public void selectOpenStorage() throws InterruptedException
	{	
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		hover.moveToElement(storageType_dropdown).build().perform();
		Thread.sleep(5000);
		BaseClass.highlightElement(storageType_dropdown);
		hover.moveToElement(storageType_dropdown).build().perform();
		Thread.sleep(2000);
		WebElement storage = wait.until(ExpectedConditions.elementToBeClickable(open_option));
		open_option.click();
		System.out.println("-> Storage type Open is selected.");
		logger.info("Selects Storage Type as Open");
	}
	
	//Check whether Out of Stock CheckBox is Enabled
	public boolean checkOutOfStockCheckbox()
	{
		return outOfStock_checkbox.isEnabled();
	}
	
	//selects Out of Stock CheckBox
	public void selectOutOfStockCheckbox()
	{
		logger.info("Checks Out of Stock CheckBox");
		BaseClass.highlightElement(outOfStock_checkbox);
		if(outOfStock_checkbox.isSelected())
		{
			outOfStock_checkbox.click();
			outOfStock_checkbox.click();
			
		}
		else
		{
			outOfStock_checkbox.click();
		}
		System.out.println("-> Excluded out of stock.");

	}
	
	//Checks whether SortBy List is Visible
	public boolean checkSortBy()
	{
		BaseClass.highlightElement(sortBy_dropdown);
		return sortBy_dropdown.isDisplayed();
		
	}
	
	//Selects Recommended from DropDown
	public void selectRecommended()
	{
		logger.info("Sorts Based on Highly Recommended");
		sortBy_dropdown.click();
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		recommended_option.click();
		System.out.println("-> Bookshelves are sorted by:"+recommended_option.getText());
		
		
	}
	
	
	//Checks and Returns the Results Displayed
		public List<WebElement> ProductName() 
		{
			logger.info("Displays First Three Products and its Prices");
			try{
				return productName;
			}catch(Exception e) {
				System.out.println("No Results Found to Display");
				return null;
			}
		}
	
		public List<WebElement> ProductPrice() 
		{
			try{
				return productPrice;
				
			}catch(Exception e) {
				System.out.println("No Results Found to Display");
				return null;
			}
		}
		
}

