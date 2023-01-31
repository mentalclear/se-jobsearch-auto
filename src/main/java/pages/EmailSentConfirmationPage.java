package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailSentConfirmationPage {
    private WebDriver driver;
    private By internalServerError = By.xpath("//h1");


    public EmailSentConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getReplyText(){
        return driver.findElement(internalServerError).getText();
    }

}
