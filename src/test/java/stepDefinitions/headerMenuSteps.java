package stepDefinitions;

import org.openqa.selenium.WebDriver;



import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HeaderMenu;



public class headerMenuSteps 
{
	WebDriver driver;
	HeaderMenu headermenu;
	String homePageURL;
	String url;
	
	//Navigates Back to Home Page
	@Given("Navigate to Homepage of the Urban Ladder Application")
	public void navigate_to_homepage_of_the_urban_ladder_application()
	{
		driver=BaseClass.getDriver();
		headermenu = new HeaderMenu(driver);
		 System.out.println("****************************");
		 System.out.println("TEST SCENARIO STARTED");
		headermenu.navigatingBack();	
	}
	
	//Hovers over Living Menu in the Header
	@When("Living Menu is hovered")
	public void living_menu_is_hovered()
	{
		headermenu.hoverLivingMenu();
	}
	
	//Collects all the SubMenu items in a List and Displays
	@When("all submenu items are collected in a list")
	public void all_submenu_items_are_collected_in_a_list()
	{
		headermenu.displayMenuItems();	
	}
	
	//Displays 
	@Then("display the items")
	public void display_the_items()
	{
		System.out.println();
		System.out.println("->From Collections, retrieved all sub-menu items under Being-At-home and stored in a List; Displayed the same");
		 System.out.println("TEST SCENARIO COMPLETED");
		 System.out.println("****************************");
	}	
}
