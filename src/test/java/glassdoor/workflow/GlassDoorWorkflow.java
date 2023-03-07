package glassdoor.workflow;

import base.BaseTestsGlassDoor;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GlassDoorWorkflow extends BaseTestsGlassDoor {
    @Test
    public void searchForQALocalJobs() {
        homePageOnGlassDoor.populateEmailField("testmeup007@gmail.com");
        homePageOnGlassDoor.clickEmailSubmitButton();
        homePageOnGlassDoor.populatePasswordField("VerySecretPA$$word2023");
        var signedInPageOnGlassDoor = homePageOnGlassDoor.clickSignInButton();
        assertEquals(signedInPageOnGlassDoor.getPageTitle(),
                "Glassdoor Job Search | Find the job that fits your life");

        String searchTerm = "Software QA engineer";
        String searchLocation = "Virginia, US";

        signedInPageOnGlassDoor.searchForJobAndLocation(searchTerm, searchLocation);
        var bufferedResultsPage = signedInPageOnGlassDoor.clickSubmitSearch();
        var allSearchResultsPage = bufferedResultsPage.clickSeeAllJobsLink();
        System.out.println(allSearchResultsPage);
    }
}
