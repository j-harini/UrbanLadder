package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.BaseClass;

public class GiftCard
{
	WebDriver driver;
	JavascriptExecutor jse;
	WebDriverWait wait;
	Select month;
	Select date;
	Logger logger;

	
	//constructor
	public GiftCard(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		BaseClass baseclass=new BaseClass();
		logger=baseclass.logElement();
	}
	
	//LOCATORS
	
	//Gift Card Label
	@FindBy(xpath="(//a[contains(text(),'Gift Cards')])[1]")
	WebElement giftCard_label;
	
	//Occasion Tile
	@FindBy(xpath="//h3[normalize-space()='Birthday/Anniversary']")
	WebElement occasion_tile;
	
	//Amount Option
	@FindBy(xpath="//button[@class=\"HuPJS\"]")
	WebElement amount_button;
	
	//Month DropDown
	@FindBy(xpath="//select[1]")
	WebElement month_dropdown;
	
	//Date DropDown
	@FindBy(xpath="//select[2]")
	WebElement date_dropdown;
	
	//Next Button
	@FindBy(xpath="//button[normalize-space()='Next']")
	WebElement next_button;

	//To Give Inputs in the Form
		//To
		//Recipient Name
		@FindBy(xpath="//input[@id='ip_4036288348']") 
		public WebElement recipientsName_Input;
		
		//Recipient Email
		@FindBy(xpath="//input[@id='ip_137656023']")
		public WebElement recipientsEmail_Input;
		
		//Recipient Mobile
		@FindBy(xpath="//input[@id='ip_3177473671']")
		public WebElement recipientsMobile_Input;
		
		//From
		//Your Name
		@FindBy(xpath="//input[@id='ip_1082986083']")
		public WebElement yourName_Input;
		
		//Your Email
		@FindBy(xpath="//input[@id='ip_4081352456']") 
		public WebElement yourEmail_Input;
		
		//Your Mobile 
		@FindBy(xpath="//input[@id='ip_2121573464']")
		public  WebElement yourMobile_Input;
		
		//Your Address
		@FindBy(xpath="//input[@id='ip_2194351474']")
		public WebElement Address_Input;
		
		//Pincode 
		@FindBy(xpath="//input[@id='ip_567727260']") 
		public WebElement pincode_Input;
		
		//Message Input
		@FindBy(xpath="//textarea[@id='ip_582840596']")
		public WebElement Message_Input;
		
	    //Submit Button
		@FindBy(xpath="//*[@type='submit']") 
		public WebElement Confirm_button;
		

	//ACTION METHODS
	
	//Selects Gift Card from Header
	public void selectGiftCard()
	{
//		try {
//			Thread.sleep(8000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		BaseClass.highlightElement(giftCard_label);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", giftCard_label);
		System.out.println();
		System.out.println("-> Gift Cards Label is Selected and Page is Navigated");
	}
	
	//Selects Birthday/Anniversary Occasion
	public void selectOccasion()
	{
		jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		BaseClass.highlightElement(occasion_tile);
		occasion_tile.click();
		System.out.println("->Birthday/Anniversary Occasion is selected");
	}
	
	//Selects Amount 
	public void chooseAmount()
	{
		BaseClass.highlightElement(amount_button);
		amount_button.click();
		System.out.println("-> Amount is Selected");
	}
	
	//Selects Month from DropDown
	public void chooseMonth() 
	{
		BaseClass.highlightElement(month_dropdown);
		month = new Select(month_dropdown);
		month.selectByIndex(2);
		System.out.println("-> Month is picked");
	}
	
	//Selects Date from DropDown
	public void chooseDate()
	{
		BaseClass.highlightElement(date_dropdown);
		date = new Select(date_dropdown);
		date.selectByIndex(14);
		System.out.println("-> Date is picked");
	}
	
	//Selects Next Button
	public void selectNext()
	{
		BaseClass.highlightElement(next_button);
		next_button.click();
	}
	
	//Enters Recipient's Name
	public void enterRecipientName(String recipientName) 
	{
		BaseClass.highlightTextBox(recipientsName_Input);
		recipientsName_Input.sendKeys(recipientName);	
	}
	
	//Enters Recipient's Mobile
	public void enterRecipientMobile(String recipientMobile)
	{
		BaseClass.highlightTextBox(recipientsMobile_Input);
		recipientsMobile_Input.sendKeys(recipientMobile);
	}

	//Enters Your Name
	public void enterYourName(String yourName)
	{
		// TODO Auto-generated method stub
		BaseClass.highlightTextBox(yourName_Input);
		yourName_Input.sendKeys(yourName);
		
	}

	//Enters Your Email
	public void enterYourEmail(String yourEmail) 
	{
		BaseClass.highlightTextBox(yourEmail_Input);
		yourEmail_Input.sendKeys(yourEmail);		
	}

	public void enterYourMobile(String yourMobile)
	{
		BaseClass.highlightTextBox(yourMobile_Input);
		yourMobile_Input.sendKeys(yourMobile);	
	}

	//Enters Address
	public void enterAddress(String address) 
	{
		BaseClass.highlightTextBox(Address_Input);
		Address_Input.sendKeys(address);
	}

	//Enters PinCode
	public void enterPincode(String pincode) 
	{
		BaseClass.highlightTextBox(pincode_Input);
		pincode_Input.sendKeys(pincode);
	}

	//Enters Recipient's Email
	public void enterRecipientEmail(String recipientEmail)
	{
		BaseClass.highlightTextBox(recipientsEmail_Input);
		recipientsEmail_Input.sendKeys(recipientEmail);
	}
	
	//Enters Confirm Button
	public void enterConfirm()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BaseClass.highlightElement(Confirm_button);
		Confirm_button.click();
		System.out.println("-> Entered All Fields");
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,0)");
		
	}	
}



