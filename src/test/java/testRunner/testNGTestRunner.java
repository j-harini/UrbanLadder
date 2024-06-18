package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"Features/displayBookshelf.feature",
				"Features/headerMenu.feature",
				"Features/giftCard.feature",
				"Features/soldout.feature"},
		glue="stepDefinitions",
		plugin = {"pretty",
				"html:Reports/CucumberHTMLReport.html",
				"json:Reports/CucumberJSONReport.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},
		monochrome = true,
		publish = true,
		dryRun = false

		)
public class testNGTestRunner extends AbstractTestNGCucumberTests {

}

