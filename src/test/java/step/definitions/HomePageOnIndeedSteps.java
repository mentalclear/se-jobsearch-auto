package step.definitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePageOnIndeed;

import static base.BaseTests.selectWebDriverForOS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageOnIndeedSteps {
    private WebDriver driver;
    private HomePageOnIndeed homepage;

    @Given("User has started Chrome browser")
    public void user_has_started_chrome_browser() {
        selectWebDriverForOS();
        driver = new ChromeDriver();
        homepage = new HomePageOnIndeed(driver);
    }

    @When("User navigates to {string}")
    public void user_navigates_to(String homepageUrl) {
        driver.get(homepageUrl);
    }

    @Then("Page title should be {string}")
    public void page_title_should_be(String homepageTitle) {
        assertEquals(homepage.getPageTitle(), homepageTitle);
    }

    @Then("{string} input field should be present")
    public void input_field_should_be_present(String fieldName) {
        assertTrue(homepage.isInputFieldPresent(fieldName));
    }

    @Then("Find jobs button should be present")
    public void button_should_be_present() {
      assertTrue(homepage.isSubmitButtonPresent());
      driver.close();
    }
}
