package glassdoor.workflow;

import base.BaseTestsGlassDoor;
import org.testng.annotations.Test;
import pages.SignedInPageOnGlassDoor;

import static org.testng.Assert.*;

public class GlassDoorWorkflow extends BaseTestsGlassDoor {
    public static SignedInPageOnGlassDoor signedInPageOnGlassDoor;

    @Test
    public void searchForQALocalJobs() {
        openGlassDoorAndSignIn();

        String searchTerm = "Software QA engineer";
        String searchLocation = "Virginia, US";
        signedInPageOnGlassDoor.searchForJobAndLocation(searchTerm, searchLocation);
        var bufferedResultsPage = signedInPageOnGlassDoor.clickSubmitSearch();
        var allSearchResultsPage = bufferedResultsPage.clickSeeAllJobsLink();
        allSearchResultsPage.setJobTypeFilterFullTime();
        allSearchResultsPage.setJobPostedTime2Weeks();
    }

    private void openGlassDoorAndSignIn() {
        homePageOnGlassDoor.populateEmailField("testmeup007@gmail.com");
        homePageOnGlassDoor.clickEmailSubmitButton();
        homePageOnGlassDoor.populatePasswordField("VerySecretPA$$word2023");
        signedInPageOnGlassDoor = homePageOnGlassDoor.clickSignInButton();
        assertEquals(signedInPageOnGlassDoor.getPageTitle(),
                "Glassdoor Job Search | Find the job that fits your life");
    }
}
