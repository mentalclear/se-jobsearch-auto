package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramePage {
    private WebDriver driver;

    public NestedFramePage(WebDriver driver) {
        this.driver = driver;
    }

    public String selectFrameLeft() {
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        String currentFrameText = getCurrentFrameText();
        driver.switchTo().defaultContent();
        return currentFrameText;
    }
    public String selectFrameBottom() {
        driver.switchTo().frame("frame-bottom");
        String currentFrameText = getCurrentFrameText();
        driver.switchTo().parentFrame();
        return currentFrameText;
    }

    private String getCurrentFrameText() {
        return driver.findElement(By.tagName("body")).getText();
    }


}
