package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllSearchResultPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AllSearchResultPage(WebDriver driver) {
        this.driver = driver;
    }
    By filterJobType = By.id("filter_jobType");
    By fullTimeOption = By.xpath("//button[@value='fulltime']");
    By fullTimeSelectedOption = By.xpath("//div[@data-test='JOBTYPE']//span[text()='Full-time']");
    By filterPostedTime = By.id("filter_fromAge");
    By postedTime2WeeksOption = By.xpath("//button[@value='14']");
    By twoWeeksSelectedOption = By.xpath("//div[@data-test='DATEPOSTED']//span[text()='Last 2 Weeks']");

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
}
