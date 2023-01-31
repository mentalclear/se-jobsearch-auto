package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage {
    private WebDriver driver;
    private By triggerAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    private By triggerConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    private By triggerPromptButton = By.xpath("//button[text()='Click for JS Prompt']");

    private By results = By.id("result");


    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void triggerAlert() {
        driver.findElement(triggerAlertButton).click();
    }

    public void triggerConfirm() {
        driver.findElement(triggerConfirmButton).click();
    }

    public void setTriggerPrompt() {
        driver.findElement(triggerPromptButton).click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }
    public String getResult() {
        return driver.findElement(results).getText();
    }
    public String getConfirmText() {
        return driver.switchTo().alert().getText();
    }

    public void setAlertInputText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }
}
