package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.OSSelector;
import utils.PageInteractions;

public class SignedInPageOnGlassDoor {
    private final WebDriver driver;
    private WebDriverWait wait;

    public SignedInPageOnGlassDoor(WebDriver driver) {
        this.driver = driver;
    }

    private final By whatSearchField = By.xpath("//input[@data-test='search-bar-keyword-input']");
    private final By whereSearchField = By.xpath("//input[@data-test='search-bar-location-input']");
    private final By submitButton = By.xpath("//button[@data-test='search-bar-submit']");
    private final By footerElement = By.id("GarnishFooter");

    public String getPageTitle() {
        var pageInteraction = new PageInteractions(driver);
        pageInteraction.scrollPageDown(footerElement);
        return driver.getTitle();
    }

    public void searchForJobAndLocation(String jobTitle, String jobLocation) {
        var os = new OSSelector();
        var keys = ((os.isLinux()) | (os.isWindows())) ? Keys.CONTROL : Keys.COMMAND;
        var whereElement = driver.findElement(whereSearchField);
        whereElement.sendKeys(keys + "a");
        whereElement.sendKeys(Keys.BACK_SPACE);
        whereElement.sendKeys(jobLocation);
        driver.findElement(whatSearchField).sendKeys(jobTitle);
    }

    public BufferResultsPageOnGlassDoor clickSubmitSearch() {
        driver.findElement(submitButton).click();
        return new BufferResultsPageOnGlassDoor(driver);
    }
}
