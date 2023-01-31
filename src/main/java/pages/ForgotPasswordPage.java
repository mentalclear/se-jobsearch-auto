package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;
    private By emailAddressField = By.id("email");
    private By retrievePasswordButton = By.id("form_submit");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTheEmail(String emailAddress) {
        driver.findElement(emailAddressField).sendKeys(emailAddress);
    }

    public EmailSentConfirmationPage clickRetrievePassword() {
        driver.findElement(retrievePasswordButton).click();
        return new EmailSentConfirmationPage(driver);
    }
}
