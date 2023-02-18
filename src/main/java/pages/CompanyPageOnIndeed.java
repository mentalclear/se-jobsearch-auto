package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CsvFileWriter;
import utils.WaitManager;

import java.util.ArrayList;
import java.util.List;

public class CompanyPageOnIndeed {
    private WebDriver driver;
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
        WaitManager waitManager = new WaitManager(driver, 5);
        CsvFileWriter fileWriter = new CsvFileWriter();
        String companyUrl = "";
        String company = "";
        List<String> companiesFound = new ArrayList<>();

        try {
            company = driver.findElement(companyName).getText();
            if (!companiesFound.contains(company)) companiesFound.add(company);
            companyUrl = driver.findElement(companySiteUrl).getAttribute("href");
        } catch (NoSuchElementException e) {}

        if (!companyUrl.isEmpty() || companiesFound.contains(company)) fileWriter.writeDataToCSV(company, companyUrl);
        scrollThePage();

        // indeed.com gets triggered when pages are opened too fast this slows the process a little.
        // >= 5 seconds is proven to be the solution.
        waitManager.dummyWait();
    }

    private void scrollThePage() {
        var bottomPageElement = driver.findElement(bottomSearch);
        String script = "arguments[0].scrollIntoView({behavior: 'smooth'})";
        ((JavascriptExecutor)driver).executeScript(script, bottomPageElement);
    }
}
