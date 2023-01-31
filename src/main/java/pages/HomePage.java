package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By whatSearchField = By.id("text-input-what");
    private By whereSearchField = By.id("text-input-where");
    private By findJobsButton = By.cssSelector("button.yosegi-InlineWhatWhere-primaryButton");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public void populateWhatField(String searchTerm) {
        driver.findElement(whatSearchField).sendKeys(searchTerm);
    }
    public void populateWhereField(String searchTerm) {
        driver.findElement(whereSearchField).sendKeys(searchTerm);
    }

    public SearchResultsPage clickFindJobs() {
        driver.findElement(findJobsButton).click();
        return new SearchResultsPage(driver);
    }

    public AlertsPage clickJavascriptAlerts() {
        clickLink("JavaScript Alerts");
        return new AlertsPage(driver);
    }
}
