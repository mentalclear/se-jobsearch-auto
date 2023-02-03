package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CsvFileWriter;

public class CompanyPageOnIndeed {
    private WebDriver driver;
    private WebDriverWait wait;
    private By companyName = By.xpath("//div[@itemprop='name']");
    private By companySiteUrl = By.xpath("//a[@data-tn-element='companyLink[]']");
    private By headerElement = By.xpath("//header[@data-tn-section='header']");
    private By bottomSearch = By.id("ipl-autocomplete-label-:Rfp:");

    public CompanyPageOnIndeed(WebDriver driver) {
        this.driver = driver;
    }
    public void closePage() {
        driver.close();
    }
    public void storeCompanyInfo() {
        CsvFileWriter fileWriter = new CsvFileWriter();
        wait = new WebDriverWait(driver, 5);
        String companyUrl = "";

        String company = driver.findElement(companyName).getText();
        try {
            companyUrl = driver.findElement(companySiteUrl).getAttribute("href");
        } catch (NoSuchElementException e) {}

        if (!companyUrl.isEmpty()) fileWriter.writeDataToCSV(company, companyUrl);
        scrollThePage();

        // indeed.com gets triggered when pages are opened too fast this slows the process a little.
        // >= 5 seconds is proven to be the solution.
        try {
            wait.until(ExpectedConditions.urlToBe("dummy-url-right-here"));
        } catch (TimeoutException e) {}
    }

    private void scrollThePage() {
        var topPageElement = driver.findElement(headerElement);
        var bottomPageElement = driver.findElement(bottomSearch);
        String script = "arguments[0].scrollIntoView()";
        ((JavascriptExecutor)driver).executeScript(script, bottomPageElement);
        ((JavascriptExecutor)driver).executeScript(script, topPageElement);
    }
}
