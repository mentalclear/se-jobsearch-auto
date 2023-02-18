package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitManager;

public class SearchResultsPageOnIndeed {
    private WebDriver driver;
    private WebDriverWait wait;
    private By remoteJobsSelector = By.id("filter-remotejob");
    private By remoteJobsMenuItem = By.xpath("//ul[@id='filter-remotejob-menu']/li");
    private By postedBySelector = By.id("filter-srctype");
    private By postedByMenuItem = By.xpath("//ul[@id='filter-srctype-menu']/li");
    private By resultingAmountOfJobs = By.xpath("//div[@class='jobsearch-JobCountAndSortPane-jobCount']/span[1]");
    private By searchResultsItems = By.xpath("//ul[@class='jobsearch-ResultsList css-0']/li");
    private By companyNameLink = By.xpath("//div[@data-company-name]/a");
    private By paginationNext = By.xpath("//a[@data-testid='pagination-page-next']");
    private By annoyingElement = By.xpath("//div[contains(@class, 'jobsearch-JapanSnackBarContainer-toastWrapper')]");

    public SearchResultsPageOnIndeed(WebDriver driver) {
        this.driver = driver;
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void setRemoteJobs() {
        driver.findElement(remoteJobsSelector).click();
        driver.findElement(remoteJobsMenuItem).click();
    }
    public void setPostedByEmployer() {
        driver.findElement(postedBySelector).click();
        driver.findElement(postedByMenuItem).click();
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
        clickSearchResultCard(index);
        try {
            clickCompanyLink();
        } catch (TimeoutException e) {
            return null;
        }
        return new CompanyPageOnIndeed(driver);
    }
    private void clickCompanyLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(companyNameLink));
        WebElement companyLinkElement = driver.findElement(companyNameLink);
        wait.until(ExpectedConditions.elementToBeClickable(companyLinkElement));
        companyLinkElement.click();
    }
    private void clickSearchResultCard(int index) {
        WebElement searchResultsElement = driver.findElements(searchResultsItems).get(index);
        wait.until(ExpectedConditions.elementToBeClickable(searchResultsElement));
        searchResultsElement.click();
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
    public void setAnnoyingElementToHidden(){
        WebElement element = driver.findElement(annoyingElement);
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('hidden','')", element);
    }
    public boolean isAnnoyingElementVisible(){
        return driver.findElement(annoyingElement).isDisplayed();
    }
}
