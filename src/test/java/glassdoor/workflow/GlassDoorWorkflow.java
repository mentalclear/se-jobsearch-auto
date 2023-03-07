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
        var searchResultsPageOnGlassDoor = homePageOnGlassDoor.clickSignInButton();
        System.out.println(searchResultsPageOnGlassDoor.getPageTitle());
        assertEquals(searchResultsPageOnGlassDoor.getPageTitle(),
                "Glassdoor Job Search | Find the job that fits your life");
    }
}
