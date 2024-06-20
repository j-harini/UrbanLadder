package stepDefinitions;
 
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import factory.BaseClass;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

 
public class Hooks 
{

	@BeforeAll
	public static void setUp() throws IOException 
	{
		BaseClass.initializeBrowser();
	}
	
	public void checkFailed(Scenario scenario)
	{
		//validate if scenario has failed
		if(scenario.isFailed()) 
		{
			final byte[] screenshot = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Failed Test");
		}	
	}
 
	@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException 
	{
		File screenshot = ((TakesScreenshot) BaseClass.getDriver() ).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
		scenario.attach(fileContent, "image/png", "screenshot");
	}
	
	@AfterAll
	public static void tearDown() 
	{		
		BaseClass.tearDown();

	}
}
