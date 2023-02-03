package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CompanyPageOnIndeed {
    private WebDriver driver;
    private By companyName = By.xpath("//div[@itemprop='name']");
    private By companySiteUrl = By.xpath("//a[@data-tn-element='companyLink[]']");

    public CompanyPageOnIndeed(WebDriver driver) {
        this.driver = driver;
    }
    public void closePage() {
        driver.close();
    }
    public void storeCompanyInfo() {
        String companyUrl = "";
        String company = driver.findElement(companyName).getText();
        try {
            companyUrl = driver.findElement(companySiteUrl).getAttribute("href");
        } catch (NoSuchElementException e) {
            System.out.println("Company website link is not present");
        }
        System.out.println("\n******************************************\n"
                + company
                + "\n"
                + companyUrl
                + "\n******************************************\n");
    }
}
