package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicLoadingExample1Page {
    private WebDriver driver;
    private WebDriverWait wait;
    private FluentWait fluentWait;
    private By startButton = By.cssSelector("#start button");
    private By resultingText = By.cssSelector("#finish h4");

    public DynamicLoadingExample1Page(WebDriver driver) {
        this.driver = driver;
    }

    public void clickStartButton() {
        driver.findElement(startButton).click();
        // explicitlyWaitForElement(resultingText);
        fluentlyWaitForElement(resultingText);
    }

    private void fluentlyWaitForElement(By resultingTextElement) {
        fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(resultingTextElement));
    }

    private void explicitlyWaitForElement(By resultingTextElement) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultingTextElement));
    }

    public String getResultText() {
        return driver.findElement(resultingText).getText();
    }
}
