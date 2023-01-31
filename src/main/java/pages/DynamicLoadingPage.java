package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPage {
    private WebDriver driver;
    private By exampleLink2 = By.linkText("Example 2: Element rendered after the fact");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
    }

    public DynamicLoadingExample1Page clickExample1() {
        driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();
        return new DynamicLoadingExample1Page(driver);
    }
    public DynamicLoadingExample2Page clickExample2(){
        driver.findElement(exampleLink2).click();
        return new DynamicLoadingExample2Page(driver);
    }

    public DynamicLoadingExample2Page rightClickOnExample2Link(){
        driver.findElement(exampleLink2).sendKeys(Keys.CONTROL, Keys.RETURN);
        return new DynamicLoadingExample2Page(driver);
    }
}
