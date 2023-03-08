package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BufferResultsPageOnGlassDoor {
    private final WebDriver driver;

    public BufferResultsPageOnGlassDoor(WebDriver driver) {
        this.driver = driver;
    }

    private final By seeAllJobsLink = By.xpath("//a[@data-test='jobs-location-see-all-link']");

    public AllSearchResultPage clickSeeAllJobsLink(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(seeAllJobsLink));
        driver.findElement(seeAllJobsLink).click();

        return new AllSearchResultPage(driver);
    }
}
