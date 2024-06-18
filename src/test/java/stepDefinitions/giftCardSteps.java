package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GiftCard;
import utilities.DataReader;

public class giftCardSteps
{
	WebDriver driver;
    GiftCard giftcard;
    List<Map<String,String>> datamap;
    DataReader reader;
    String filePath = System.getProperty("user.dir")+"\\testData\\UrbanLadderTestData.xlsx";
	
    @Given("Select Gift Card Label")
	public void select_gift_card_label() 
	{
		driver=BaseClass.getDriver();
		giftcard = new GiftCard(driver);
		System.out.println("****************************");
		 System.out.println("TEST SCENARIO STARTED");
		try {
			Thread.sleep(10);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		giftcard.selectGiftCard();
	}

	@When("Select Birthday\\/Anniversary")
	public void select_birthday_anniversary() 
	{
		giftcard.selectOccasion();
	}

	@When("Choose Amount, Date and Time")
	public void choose_amount_date_and_time() 
	{
		giftcard.chooseAmount();
		giftcard.chooseMonth();
		giftcard.chooseDate();
		giftcard.selectNext();
	}

	@When("Fill the details with excel sheet {string} and excel row {int}")
	public void fill_the_details_with_excel_sheet_and_excel_row(String sheetName, int rowNumber)
	{
		reader = new DataReader();
	    try
	    {
			datamap=reader.getData(filePath,sheetName);
		} catch (InvalidFormatException e)
	    {
			e.printStackTrace();
		} catch (IOException e)
	    {
			e.printStackTrace();
		}
		
		String RecipientName = datamap.get(rowNumber).get("Recipient's Name");
		String RecipientEmail = datamap.get(rowNumber).get("Recipient's Email");
		String RecipientMobile = datamap.get(rowNumber).get("Recipient's Mobile");
		String YourName = datamap.get(rowNumber).get("Your Name");
		String YourEmail = datamap.get(rowNumber).get("Your Email");
		String YourMobile = datamap.get(rowNumber).get("Your Mobile");
		String Address = datamap.get(rowNumber).get("Enter Address");
		String Pincode = datamap.get(rowNumber).get("Pincode");
		
		giftcard.enterRecipientName(RecipientName);
		giftcard.enterRecipientEmail(RecipientEmail);
		giftcard.enterRecipientMobile(RecipientMobile);
		giftcard.enterYourName(YourName);
		giftcard.enterYourEmail(YourEmail);
		giftcard.enterYourMobile(YourMobile);
		giftcard.enterAddress(Address);
		giftcard.enterPincode(Pincode);
		giftcard.enterConfirm();	
		
	}
	

	@Then("Validate the Displayed Details {string}")
	public void validate_the_displayed_details(String message)
	{   
		System.out.println("-> Inputs are "+message);
		if(message.equalsIgnoreCase("Invalid"))
		{
			try
			{
				System.out.println("-> Displaying Error Message");
				String text = giftcard.yourEmail_Input.getAttribute("validationMessage");
				System.out.println(text+"\n");
				DataReader.setCellData(filePath, "GiftCard",1,9,"Failed");					
				DataReader.fillRedColor(filePath, "GiftCard",1,9);
				//DataReader.setCellData(filePath, "GiftCard",3,9,"Failed");					
				//DataReader.fillRedColor(filePath, "GiftCard",3,9);
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
		else if(message.equalsIgnoreCase("Valid"))
		{
				System.out.println("-> No error. Form is filled");
				String text = giftcard.yourEmail_Input.getAttribute("validationMessage");
				System.out.println(text+"\n");
				
				try 
				{
					String data = "Passed";
					DataReader.setCellData(filePath, "GiftCard",2,9,data);
					DataReader.fillGreenColor(filePath, "GiftCard",2,9);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}					
		}
		 System.out.println("TEST SCENARIO COMPLETED");
		 System.out.println("****************************");
		
		}
	}


