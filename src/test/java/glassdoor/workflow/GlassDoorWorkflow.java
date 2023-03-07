package glassdoor.workflow;

import base.BaseTestsGlassDoor;
import org.testng.annotations.Test;
import pages.AllSearchResultPage;
import pages.AllSearchResultPage.*;
import pages.SignedInPageOnGlassDoor;
import utils.CsvFileWriter;
import static org.testng.Assert.*;


public class GlassDoorWorkflow extends BaseTestsGlassDoor {
    private SignedInPageOnGlassDoor signedInPageOnGlassDoor;
    private AllSearchResultPage allSearchResultsPage;

    @Test
    public void searchForLocalJobs() {
        openGlassDoorAndSignIn();

        String searchTerm = "Software Quality Engineer";
        String searchLocation = "Virginia, US";
        CsvFileWriter outputFile = new CsvFileWriter(
                getClearedTitle(searchTerm) + "_" + getClearedTitle(searchLocation));

        signedInPageOnGlassDoor.searchForJobAndLocation(searchTerm, searchLocation);
        var bufferedResultsPage = signedInPageOnGlassDoor.clickSubmitSearch();
        allSearchResultsPage = bufferedResultsPage.clickSeeAllJobsLink();
        allSearchResultsPage.setJobTypeFilterFullTime();
        allSearchResultsPage.setJobPostedTime2Weeks();

        extractCompaniesData(outputFile);
    }

    private void extractCompaniesData(CsvFileWriter file) {
        int linksToProcess = allSearchResultsPage.getResultsSize();
        while(linksToProcess > 0) {
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
