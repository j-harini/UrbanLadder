package stepDefinitions;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;


public class displayBookshelfSteps 
{	
	List<WebElement> productNames ;
	List<WebElement> productPrices;
    WebDriver driver;
    HomePage bk;
    
		@Given("Bookshelf is selected from the submenu")
		public void bookshelf_is_selected_from_the_submenu() throws InterruptedException
		{
			driver=BaseClass.getDriver();
			bk = new HomePage(driver);
			System.out.println("****************************");
			System.out.println("TEST SCENARIO STARTED");
			bk.checkLivingMenu();
			bk.hoverLivingMenu();
			bk.checkBookshelves();
			bk.selectBookshelves();
			bk.handleAlert();
		}

		@When("Price Range is set below Rs15000")
		public void price_range_is_set_below_rs15000() throws InterruptedException 
		{
			bk.checkPriceFilter();	
			bk.setPrice();
		}

		@When("Select Storage Type \\(open)")
		public void select_storage_type_open() throws InterruptedException 
		{
			bk.selectOpenStorage();
		}

		@When("Exclude out of stock")
		public void exclude_out_of_stock() 
		{
			bk.checkOutOfStockCheckbox();
			bk.selectOutOfStockCheckbox();
		}

		@When("Sort By Recommended")
		public void sort_by_recommended()
		{
			bk.checkSortBy();
			bk.selectRecommended();
		}

		@Then("Print all details in console output")
		public void print_all_details_in_console_output()
		{
			bk.ProductName();
			bk.ProductPrice();
			System.out.println("-> Displaying the name & price of first 3 Bookshelves below Rs. 15000, with Storage type as open & excluded out of stock");
			    for (int i = 0; i < 3; i++) 
			    {
			         //Access elements at index i
			        System.out.println(i +1+") Product Name: " + bk.ProductName().get(i).getText());
			        System.out.println("Product Price: " + bk.ProductPrice().get(i).getText());
			    }
			 System.out.println("TEST SCENARIO COMPLETED");
			 System.out.println("****************************");	
 		}
	}

