package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitManager;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By remoteJobsSelector = By.id("filter-remotejob");
    private final By remoteJobsMenuItem = By.xpath("//ul[@id='filter-remotejob-menu']/li");
    private final By resultingAmountOfJobs = By.xpath("//div[@class='jobsearch-JobCountAndSortPane-jobCount']/span[1]");
    private final By searchResultsItems = By.xpath("//ul[@class='jobsearch-ResultsList css-0']/li");
    // private By searchResultsItems = By.xpath("//ul[@class='jobsearch-ResultsList css-0']/li/descendant-or-self::a");
    private final By companyNameLink = By.xpath("//div[@data-company-name]/a");
    private final By paginationNext = By.xpath("//a[@data-testid='pagination-page-next']");


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
        WebElement searchResultsElement = driver.findElements(searchResultsItems).get(index);
        wait.until(ExpectedConditions.elementToBeClickable(searchResultsElement));
        searchResultsElement.click();
        System.out.println("1 Clicked on: " + searchResultsElement.getText());

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(companyNameLink));
            WebElement companyLinkElement = driver.findElement(companyNameLink);
            wait.until(ExpectedConditions.elementToBeClickable(companyLinkElement));
            companyLinkElement.click();
            System.out.println("2 Clicked on: " + companyLinkElement.getText());
        } catch (TimeoutException e) {
            return null;
        }

        return new CompanyPageOnIndeed(driver);
    }

    public void getToNextResultsPage() {
        driver.findElement(paginationNext).click();
    }

    public void scrollThePage() {
        WaitManager waitManager = new WaitManager(driver, 2);
        var bottomPageElement = driver.findElement(paginationNext);
        String script = "arguments[0].scrollIntoView({behavior: 'smooth'})";
        ((JavascriptExecutor)driver).executeScript(script, bottomPageElement);
        waitManager.dummyWait();
    }
}
