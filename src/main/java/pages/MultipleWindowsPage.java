package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MultipleWindowsPage {

    public MultipleWindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;
    private By clickHereLink = By.linkText("Click Here");

    public void clickHere() {
        driver.findElement(clickHereLink).click();
    }

}
