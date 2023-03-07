package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BufferResultsPageOnGlassDoor {
    private WebDriver driver;
    private WebDriverWait wait;

    public BufferResultsPageOnGlassDoor(WebDriver driver) {
        this.driver = driver;
    }

    By seeAllJobsLink = By.xpath("//a[@data-test='jobs-location-see-all-link']");

    public AllSearchResultPage clickSeeAllJobsLink(){
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(seeAllJobsLink));
        driver.findElement(seeAllJobsLink).click();

        return new AllSearchResultPage(driver);
    }
}
