package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.BaseClass;

public class LetMeKnowForm
{

	WebDriver driver;
	Actions hover;
	WebDriverWait wait;
	Logger logger;

	
	//constructor
	public LetMeKnowForm(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		BaseClass baseclass=new BaseClass();
		logger=baseclass.logElement();
	}
	
	//LOCATORS
	
	//SortBy DropDown
	@FindBy(xpath="//span[normalize-space()='Recommended']") 
	public WebElement SortBy_DropDown;

	//Sort By High To Low 
	@FindBy(xpath = "//li[normalize-space()='Price: Low to High']") 
	public WebElement LowtoHigh_option;
	
	//Sold Out Ribbon
	@FindBy(xpath="(//li[1]//div[1]//div[3]//div)[1]")
	WebElement soldOut_ribbon;
	
	//PopUp box
	@FindBy(xpath="//*[@id='login_dialog']") 
	WebElement alert_popup;
		
	//close button
	@FindBy(xpath="//a[@class=\"close-reveal-modal hide-mobile\"]")
	WebElement alert_close;
	
	//Email Address TextBox
	@FindBy(xpath="(//input[@id='email_id'])[1]")
	WebElement txtEmailAddress;
		
	//Number TextBox
	@FindBy(xpath="(//input[@id='phoneno'])[1]")
	WebElement txtNumber;
	
	//Enter Button
	@FindBy(xpath="(//input[@value=\"LET ME KNOW\"])[1]")
	WebElement submit;
		
	//Success Text Message
	@FindBy(xpath="(//div[@class=\"after_submit_soldout text-center success_msg\"])[1]")
	WebElement success_text;
		
	//SearchBox
	@FindBy(xpath="//input[@id='search']")
	WebElement search_box;
	
	//Search Icon
	@FindBy(xpath="//span[@class='search-icon icofont-search']")
	WebElement search_icon;

	
	//ACTIONS
		
	//Enters Search value into Search Box
	public void enterSearchValue(String search)
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseClass.highlightElement(search_box);
		search_box.sendKeys(search);
	}
	
	//Clicks Search Icon
	public void enterSearch()
	{
		search_icon.click();
		logger.info("Enters Search String Value from Feature File");
	}
	
	//Selects DropDown
	public void selectDropdown()
	{
		logger.info("Selects DropDown");
		BaseClass.highlightElement(SortBy_DropDown);
		SortBy_DropDown.click();

	}
	
	//Sorts By Price: Low to High
	public void priceLowtoHigh()
	{
		logger.info("Sorts Price from Low to High");
		BaseClass.highlightElement(LowtoHigh_option);
		LowtoHigh_option.click();
	}
	
	//Clicks Sold Out Message Ribbon
	public void clicksoldOut_ribbon() 
	{
		logger.info("Selects the Out of Stock Ribbon");
		BaseClass.highlightElement(soldOut_ribbon);
		soldOut_ribbon.click();
	}
	
	//Handles PopUp
	public void handlePopUp() throws InterruptedException
	{
		logger.info("Handles PopUp");
		Thread.sleep(10000);
		BaseClass.highlightElement(alert_popup);
		System.out.println("-> Popup Found:"+alert_popup.isDisplayed());
		if(alert_popup.isDisplayed())
		{
			BaseClass.highlightElement(alert_close);
			alert_close.click();
			System.out.println("->Popup is closed.");
		}
	}
	
	//Enters Email in the TextBox
	public void enterEmail(String email) 
	{
		logger.info("Enters Mail ID from the Data Table in Feature File");
		
		try 
		{
			Thread.sleep(5000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		BaseClass.highlightTextBox(txtEmailAddress);
		txtEmailAddress.sendKeys(email);
	}

	//Enters Number in the TextBox
	public void enterNumber(String number) 
	{
		logger.info("Enters Number from the Data Table in Feature File");

		BaseClass.highlightTextBox(txtNumber);
		txtNumber.sendKeys(number);
		
	}
	public void enterSubmit()
	{
		logger.info("Enters Let Me Know Button after filling the fields");
		BaseClass.highlightElement(submit);
		submit.click();
	}
	
	//Gets the Success Message
	public String message()
	{
		logger.info("Validating Success Message");
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		BaseClass.highlightElement(success_text);
		return success_text.getText();
	}
	
}
