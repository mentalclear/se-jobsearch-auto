package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HorizontalSliderPage {
    private WebDriver driver;
    private By slider = By.cssSelector(".sliderContainer input");
    private By range = By.id("range");

    public HorizontalSliderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     *
     * @param targetValue should be between 0 and 5, can be double but incremented in .5 only
     */
    public void changeRange(double targetValue) throws InterruptedException {
        var sliderElement = driver.findElement(slider);
        for (double i = 0; i < targetValue; i += 0.5) {
            sliderElement.sendKeys(Keys.ARROW_RIGHT);
        }
    }
    public double verifyTheValue() {
        return Double.valueOf(driver.findElement(range).getText());
    }
}
