package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private By whatSearchField = By.id("text-input-what");
    private By whereSearchField = By.id("text-input-where");
    private By whereFieldButton = By.xpath("//input[@id='text-input-where']/following-sibling::span");
    private By findJobsButton = By.cssSelector("button.yosegi-InlineWhatWhere-primaryButton");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void populateWhatField(String searchTerm) {
        driver.findElement(whatSearchField).sendKeys(searchTerm);
    }
    public void populateWhereField(String searchTerm) {
        WebElement fieldElement = driver.findElement(whereSearchField);
        fieldElement.click();
        driver.findElement(whereFieldButton).click();
        fieldElement.sendKeys(searchTerm);
    }
    public SearchResultsPage clickFindJobs() {
        driver.findElement(findJobsButton).click();
        return new SearchResultsPage(driver);
    }
}
