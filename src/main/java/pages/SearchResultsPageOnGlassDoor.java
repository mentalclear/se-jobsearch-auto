package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPageOnGlassDoor {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResultsPageOnGlassDoor(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        scrollPageDown();
        return driver.getTitle();
    }

    private void scrollPageDown() {
        var bottomPageElement = driver.findElement(By.id("GarnishFooter"));
        String script = "arguments[0].scrollIntoView({behavior: 'smooth'})";
        ((JavascriptExecutor)driver).executeScript(script, bottomPageElement);
    }
}
