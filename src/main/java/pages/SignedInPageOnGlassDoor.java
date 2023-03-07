package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignedInPageOnGlassDoor {
    private WebDriver driver;
    private WebDriverWait wait;

    public SignedInPageOnGlassDoor(WebDriver driver) {
        this.driver = driver;
    }

    By whatSearchField = By.xpath("//input[@data-test='search-bar-keyword-input']");
    By whereSearchField = By.xpath("//input[@data-test='search-bar-location-input']");
    By submitButton = By.xpath("//button[@data-test='search-bar-submit']");

    public String getPageTitle() {
        scrollPageDown();
        return driver.getTitle();
    }

    private void scrollPageDown() {
        var bottomPageElement = driver.findElement(By.id("GarnishFooter"));
        String script = "arguments[0].scrollIntoView({behavior: 'smooth'})";
        ((JavascriptExecutor)driver).executeScript(script, bottomPageElement);
    }

    public void searchForJobAndLocation(String jobTitle, String jobLocation) {
       driver.findElement(whatSearchField).sendKeys(jobTitle);
       var whereElement = driver.findElement(whereSearchField);
       whereElement.sendKeys(Keys.COMMAND + "a");
       whereElement.sendKeys(Keys.BACK_SPACE);
       whereElement.sendKeys(jobLocation);
    }

    public BufferResultsPageOnGlassDoor clickSubmitSearch() {
        driver.findElement(submitButton).click();
        return new BufferResultsPageOnGlassDoor(driver);
    }
}
