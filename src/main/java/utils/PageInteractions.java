package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PageInteractions {
    private final WebDriver driver;

    public PageInteractions(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollPageDown(By element) {
        var bottomPageElement = driver.findElement(element);
        String script = "arguments[0].scrollIntoView({behavior: 'smooth'})";
        ((JavascriptExecutor) driver).executeScript(script, bottomPageElement);
    }
}
