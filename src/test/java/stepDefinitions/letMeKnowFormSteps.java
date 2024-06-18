package stepDefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LetMeKnowForm;

public class letMeKnowFormSteps 
{
    WebDriver driver;
    LetMeKnowForm Form;
    //Data driven
    Map<String, String>datamap;
    
    //Enters the String Value into the Search Box and Searches
	@Given("Search {string} in the Homepage")
	public void search_in_the_homepage(String search) 
	{
		driver=BaseClass.getDriver();
		Form = new LetMeKnowForm(driver);
		System.out.println("****************************");
		System.out.println("TEST SCENARIO STARTED");
		Form.enterSearchValue(search);
		Form.enterSearch();
		System.out.println("-> Searching "+search);
		
	}

	//Filters the Products
	@When("item is Out of Stock")
	public void item_is_out_of_stock() throws InterruptedException 
	{
		Form.selectDropdown();
		Form.priceLowtoHigh();
		Form.handlePopUp();
		Form.clicksoldOut_ribbon();		
	}

	//Fills the Let Me Know Form with Email and Number and Validate the Success Message
	@Then("Fill the Let Me Know Form with below detail.")
	public void fill_the_let_me_know_form_with_below_detail(DataTable dataTable)
	{
		datamap=dataTable.asMap(String.class, String.class);
		Form.enterEmail(datamap.get("email"));
		Form.enterNumber(datamap.get("number"));
		Form.enterSubmit();
		String message = Form.message();
		System.out.println("-> Message: "+message);
		Assert.assertEquals(message.contains("Thank You."), true);
		System.out.println("****************************");
		System.out.println("TEST SCENARIO COMPLETED");		
	}
}
