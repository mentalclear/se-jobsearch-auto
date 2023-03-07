package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CsvFileWriter;

public class AllSearchResultPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AllSearchResultPage(WebDriver driver) {
        this.driver = driver;
    }
    private By filterJobType = By.id("filter_jobType");
    private By fullTimeOption = By.xpath("//button[@value='fulltime']");
    private By fullTimeSelectedOption = By.xpath("//div[@data-test='JOBTYPE']//span[text()='Full-time']");
    private By filterPostedTime = By.id("filter_fromAge");
    private By postedTime2WeeksOption = By.xpath("//button[@value='14']");
    private By twoWeeksSelectedOption = By.xpath("//div[@data-test='DATEPOSTED']//span[text()='Last 2 Weeks']");
    private By resultingAmountOfJobs = By.xpath("//h1[@data-test='jobCount-H1title']");
    private By jobResultsList = By.xpath("//ul[@data-test='jlGrid']//li");
    private By paginationNextButton = By.xpath("//button[@data-test='pagination-next']");

    public void setJobTypeFilterFullTime(){
        setJobFilter(filterJobType, fullTimeOption, fullTimeSelectedOption);
    }
    public void setJobPostedTime2Weeks(){
        setJobFilter(filterPostedTime, postedTime2WeeksOption, twoWeeksSelectedOption);
    }
    private void setJobFilter(By selector, By option, By result){
        wait = new WebDriverWait(driver, 5);
        driver.findElement(selector).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(option));
        driver.findElement(option).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(result));
    }

    public String getResultingNumberOfJobs() {
        return driver.findElement(resultingAmountOfJobs).getText().split(" ")[0];
    }
    public int getResultsSize() {
        return driver.findElements(jobResultsList).size();
    }
    public Boolean isPaginationNextVisible() {
        return driver.findElement(paginationNextButton).isEnabled();
    }

    public void getToNextResultsPage() {
        driver.findElement(paginationNextButton).click();
    }
    public CompanyProfilePane clickListItemCompanyLink(int index) {
        wait = new WebDriverWait(driver, 5);
        clickSearchResultCard(index);
        return new CompanyProfilePane(driver);
    }
    private void clickSearchResultCard(int index) {
        WebElement searchResultsElement = driver.findElements(jobResultsList).get(index);
        wait.until(ExpectedConditions.elementToBeClickable(searchResultsElement));
        searchResultsElement.click();
    }
    public class CompanyProfilePane{
        private WebDriver driver;
        private By companyName = By.xpath("//div[@data-test='employerName']");
        private By companySiteUrl = By.cssSelector("div#CompanyContainer a");

        public CompanyProfilePane(WebDriver driver) {
            this.driver = driver;
        }
        public void storeCompanyInfo(CsvFileWriter fileWriter) {
            wait = new WebDriverWait(driver, 5);
            String companyUrl = "";
            String company = "";
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(companyName));
                company = driver.findElement(companyName).getText().split("\n| ")[0];
                companyUrl = driver.findElement(companySiteUrl).getAttribute("href");
            } catch (NoSuchElementException e) {
                System.out.println("Couldn't locate company or company URL... That happens, it's Ok! ");
            }
            if (!companyUrl.isEmpty()) fileWriter.writeDataToCSV(company, companyUrl);
        }
    }
}
