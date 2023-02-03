package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By remoteJobsSelector = By.id("filter-remotejob");
    private By remoteJobsMenuItem = By.xpath("//ul[@id='filter-remotejob-menu']/li");
    private By resultingAmountOfJobs = By.xpath("//div[@class='jobsearch-JobCountAndSortPane-jobCount']/span[1]");
    private By searchResultsItems = By.xpath("//ul[@class='jobsearch-ResultsList css-0']/li");
    // private By searchResultsItems = By.xpath("//ul[@class='jobsearch-ResultsList css-0']/li/descendant-or-self::a");
    private By companyNameLink = By.xpath("//div[@data-company-name]/a");
    private By paginationNext = By.xpath("//a[@data-testid='pagination-page-next']");


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public void setRemoteJobs() {
        driver.findElement(remoteJobsSelector).click();
        driver.findElement(remoteJobsMenuItem).click();
    }

    public String getRemoteJobsPillStyle() {
        return driver.findElement(remoteJobsSelector).getCssValue("background-color");
    }

    public String getResultingNumberOfJobs() {
        return driver.findElement(resultingAmountOfJobs).getText();
    }

    public int getResultsSize() {
        return driver.findElements(searchResultsItems).size();
    }

    public Boolean isPaginationNextVisible() {
        return driver.findElements(paginationNext).size() > 0;
    }

    public CompanyPageOnIndeed clickListItemCompanyLink(int index) {
        wait = new WebDriverWait(driver, 5);

        // driver.findElements(searchResultsItems).get(index).click();
        WebElement element = driver.findElements(searchResultsItems).get(index);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(companyNameLink));
        driver.findElement(companyNameLink).click();

        return new CompanyPageOnIndeed(driver);
    }

    public void getToNextResultsPage() {
        driver.findElement(paginationNext).click();
    }
}
