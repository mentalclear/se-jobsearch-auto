package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By remoteJobsSelector = By.id("filter-remotejob");
    private By remoteJobsMenuItem = By.xpath("//ul[@id='filter-remotejob-menu']/li");
    private By resultingAmountOfJobs = By.xpath("//div[@class='jobsearch-JobCountAndSortPane-jobCount']/span[1]");
    private By searchResultsItems = By.xpath("//ul[@class='jobsearch-ResultsList css-0']/li");
    private By companyNameLink = By.xpath("//div[@data-company-name]/a");


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

    public int getListOfSearchResultsSize() {
        return driver.findElements(searchResultsItems).size();
    }


    public CompanyPageOnIndeed clickListItemCompanyLink(int index) {
        wait = new WebDriverWait(driver, 2);
        String originalWindow = driver.getWindowHandle();

        driver.findElements(searchResultsItems).get(index).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(companyNameLink));

        driver.findElement(companyNameLink).click();

        wait.until(numberOfWindowsToBe(2));

        // Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        // driver.switchTo().window(originalWindow);
        System.out.println("********!!!!" + driver.getTitle());
        return new CompanyPageOnIndeed(driver);
    }
}
