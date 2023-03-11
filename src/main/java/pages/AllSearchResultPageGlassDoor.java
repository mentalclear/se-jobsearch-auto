package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CsvFileWriter;
import utils.PageInteractions;

public class AllSearchResultPageGlassDoor {
    private final WebDriver driver;
    private WebDriverWait wait;

    public AllSearchResultPageGlassDoor(WebDriver driver) {
        this.driver = driver;
    }

    private final By filterJobType = By.id("filter_jobType");
    private final By fullTimeOption = By.xpath("//button[@value='fulltime']");
    private final By fullTimeSelectedOption = By.xpath("//div[@data-test='JOBTYPE']//span[text()='Full-time']");
    private final By filterPostedTime = By.id("filter_fromAge");
    private final By postedTime2WeeksOption = By.xpath("//button[@value='14']");
    private final By twoWeeksSelectedOption = By.xpath("//div[@data-test='DATEPOSTED']//span[text()='Last 2 Weeks']");
    private final By jobResultsList = By.xpath("//ul[@data-test='jlGrid']//li");
    private final By paginationNextButton = By.xpath("//button[@data-test='pagination-next']");
    private final By jobAlertElement = By.id("InlineJobAlert");
    private final By estimatedSalaryModal = By.xpath("//div[@data-test='Estimated-Salary']//div/span");


    public void setJobTypeFilterFullTime() {
        setJobFilter(filterJobType, fullTimeOption, fullTimeSelectedOption);
    }

    public void setJobPostedTime2Weeks() {
        setJobFilter(filterPostedTime, postedTime2WeeksOption, twoWeeksSelectedOption);
    }

    private void setJobFilter(By selector, By option, By result) {
        wait = new WebDriverWait(driver, 60);
        driver.findElement(selector).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(option));
        driver.findElement(option).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(result));
    }

    public int getResultsSize() {
        return driver.findElements(jobResultsList).size();
    }

    public Boolean isPaginationNextVisible() {
        return driver.findElement(paginationNextButton).isEnabled();
    }

    public void getToNextResultsPage() {
        wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(estimatedSalaryModal));
            driver.findElement(estimatedSalaryModal).click();
        } catch (TimeoutException|NoSuchElementException ignored) {}

        wait.until(ExpectedConditions.invisibilityOfElementLocated(estimatedSalaryModal));
        driver.findElement(paginationNextButton).click();
    }

    public CompanyProfilePane clickListItemCompanyLink(int index) {
        wait = new WebDriverWait(driver, 60);
        clickSearchResultCard(index);
        return new CompanyProfilePane(driver);
    }

    private void clickSearchResultCard(int index) {
        try {
            WebElement searchResultsElement = driver.findElements(jobResultsList).get(index);
            wait.until(ExpectedConditions.visibilityOf(searchResultsElement));
            wait.until(ExpectedConditions.elementToBeClickable(searchResultsElement));
            searchResultsElement.click();
        } catch (Exception e) {
            System.out.printf("This happened %s", e);
        }
    }

    public void scrollPageDown() {
        var pageInteraction = new PageInteractions(driver);
        pageInteraction.scrollPageDown(jobAlertElement);
    }

    public class CompanyProfilePane {
        private final WebDriver driver;
        private final By companyName = By.xpath("//div[@data-test='employerName']");
        private final By companySiteUrl = By.cssSelector("div#CompanyContainer a");

        public CompanyProfilePane(WebDriver driver) {
            this.driver = driver;
        }

        public void storeCompanyInfo(CsvFileWriter fileWriter) {
            wait = new WebDriverWait(driver, 360);
            String companyUrl = "";
            String company = "";
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#JDCol > div > span")));
                wait.until(ExpectedConditions.visibilityOfElementLocated(companyName));
                company = driver.findElement(companyName).getText().split("\n")[0];
                companyUrl = driver.findElement(companySiteUrl).getAttribute("href");
            } catch (NoSuchElementException e) {
                System.out.println("Couldn't locate company or company URL... That happens, it's Ok! ");
            }
            if (!companyUrl.isEmpty()) fileWriter.writeDataToCSV(company, companyUrl);
        }
    }
}
