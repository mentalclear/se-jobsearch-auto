package glassdoor.workflow;

import base.BaseTestsGlassDoor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AllSearchResultPage;
import pages.AllSearchResultPage.*;
import pages.SignedInPageOnGlassDoor;
import utils.CsvFileWriter;
import static org.testng.Assert.*;

public class GlassDoorWorkflowTests extends BaseTestsGlassDoor {
    private SignedInPageOnGlassDoor signedInPageOnGlassDoor;
    private AllSearchResultPage allSearchResultsPage;
    @BeforeClass
    public void setupGlassDoorSuite(){
        openGlassDoorAndSignIn();
    }
    @DataProvider(name = "SearchTermsQE")
    public Object[][] createQEData(){
        return new Object[][] {
                {"Quality Engineer", "Virginia, US"},
                {"Quality Engineer", "Maryland, US"},
                {"Quality Engineer", "Washington DC, US"},
                {"Software QA", "Virginia, US"},
                {"Software QA", "Maryland, US"},
                {"Software QA", "Washington DC, US"},
                {"QA Engineer", "Virginia, US"},
                {"QA Engineer", "Maryland, US"},
                {"QA Engineer", "Washington DC, US"},
                {"Software Quality Engineer", "Virginia, US"},
                {"Software Quality Engineer", "Maryland, US"},
                {"Software Quality Engineer", "Washington DC, US"},
                {"Software Tester", "Virginia, US"},
                {"Software Tester", "Maryland, US"},
                {"Software Tester", "Washington DC, US"},
                {"Software Test Engineer", "Virginia, US"},
                {"Software Test Engineer", "Maryland, US"},
                {"Software Test Engineer", "Washington DC, US"}
        };
    }
    @Test(dataProvider = "SearchTermsQE", priority = 1)
    public void searchForJobs(String jobTitle, String jobLocation) {
        var outputFile = new CsvFileWriter(getClearedTitle(jobTitle)
                + "_" + getClearedTitle(jobLocation));

        signedInPageOnGlassDoor.searchForJobAndLocation(jobTitle, jobLocation);
        var bufferedResultsPage = signedInPageOnGlassDoor.clickSubmitSearch();
        allSearchResultsPage = bufferedResultsPage.clickSeeAllJobsLink();
        allSearchResultsPage.setJobTypeFilterFullTime();
        allSearchResultsPage.setJobPostedTime2Weeks();

        extractCompaniesData(outputFile);
    }
    private void extractCompaniesData(CsvFileWriter file) {
        int linksToProcess = allSearchResultsPage.getResultsSize();
        while(linksToProcess > 1) {
            for (int i = 0; i < allSearchResultsPage.getResultsSize(); i++) {
                CompanyProfilePane companyProfilePane = allSearchResultsPage.clickListItemCompanyLink(i);
                companyProfilePane.storeCompanyInfo(file);
                linksToProcess--;
            }
            if (allSearchResultsPage.isPaginationNextVisible()) {
                allSearchResultsPage.getToNextResultsPage();
                linksToProcess = allSearchResultsPage.getResultsSize();
            }
        }
    }
    private void openGlassDoorAndSignIn() {
        homePageOnGlassDoor.populateEmailField("testmeup007@gmail.com");
        homePageOnGlassDoor.clickEmailSubmitButton();
        homePageOnGlassDoor.populatePasswordField("VerySecretPA$$word2023");
        signedInPageOnGlassDoor = homePageOnGlassDoor.clickSignInButton();
        assertEquals(signedInPageOnGlassDoor.getPageTitle(),
                "Glassdoor Job Search | Find the job that fits your life");
    }
    private String getClearedTitle(String title) {
        return title.trim().replaceAll("[,.\\s]", "_");
    }
}
