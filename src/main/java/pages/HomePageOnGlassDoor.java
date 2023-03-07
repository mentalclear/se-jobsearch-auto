package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageOnGlassDoor {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePageOnGlassDoor(WebDriver driver) {
        this.driver = driver;
    }

    private By emailSignInFormField = By.id("inlineUserEmail");
    private By emailSubmitButton = By.xpath("//button[@data-testid='email-form-button']");
    private By passwordLabel = By.xpath("//label[@for='inlineUserPassword']");
    private By passwordSignInFormField = By.xpath("//input[@data-test='passwordInput']");
    private By signInButton = By.xpath("//div[@class='slider-enter-done']//div//div//button");

    public void populateEmailField(String emailAddress){
        wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(emailSignInFormField));
            driver.findElement(emailSignInFormField).sendKeys(emailAddress);
        } catch (NoSuchElementException e) {
            System.out.printf("Selector %s isn't visible...", emailSignInFormField);
        }
    }
    public void clickEmailSubmitButton(){
        driver.findElement(emailSubmitButton).click();
    }
    public void populatePasswordField(String password){
        scrollPageDown();

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(passwordLabel)));
        System.out.println(driver.findElement(passwordLabel).isDisplayed());
        var labelElement = driver.findElement(passwordLabel);
        labelElement.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(passwordSignInFormField));
        wait.until(ExpectedConditions.elementToBeClickable(passwordSignInFormField));
        var element = driver.findElement(passwordSignInFormField);
        element.sendKeys(password);
    }
    private void scrollPageDown() {
        var bottomPageElement = driver.findElement(By.id("FooterModule"));
        String script = "arguments[0].scrollIntoView({behavior: 'smooth'})";
        ((JavascriptExecutor)driver).executeScript(script, bottomPageElement);
    }
    public SearchResultsPageOnGlassDoor clickSignInButton(){
        driver.findElement(signInButton).click();
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@data-test='profile-container']")));
        return new SearchResultsPageOnGlassDoor(driver);
    }
}
