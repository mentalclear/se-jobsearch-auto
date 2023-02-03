package workflow;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.CompanyPageOnIndeed;
import pages.SearchResultsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WorkflowTests extends BaseTests {
    private SearchResultsPage searchResultsPage;
    private CompanyPageOnIndeed companyPageOnIndeed;

    @Test(priority = 1)
    public void searchIndeedJobsTest() {
        String jobTitle = "Quality Engineer";
        String jobLocation = "United States";
        startHomePageSearch("https://www.indeed.com/jobs", jobLocation, jobTitle);
        setSearchToRemoteJobs();

        int resultsSize = searchResultsPage.getListOfSearchResultsSize();
        for (int i = 0; i < resultsSize-1; i++) {
            if (i == 5 || i == 11) continue;
            CompanyPageOnIndeed companyPage = searchResultsPage.clickListItemCompanyLink(i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            companyPage.storeCompanyInfo();
            companyPage.closePage();
            getWindowManager().switchToTab(jobTitle + " Jobs, Employment in "+ jobLocation +" | Indeed.com");
        }
    }
    private void startHomePageSearch(String jobSearchUrl, String whereFieldValue, String whatFieldValue) {
        homePage.populateWhereField(whereFieldValue);
        homePage.populateWhatField(whatFieldValue);
        searchResultsPage = homePage.clickFindJobs();
        assertTrue(searchResultsPage.getCurrentUrl().contains(jobSearchUrl));
    }

    private void setSearchToRemoteJobs() {
        searchResultsPage.setRemoteJobs();
        assertEquals(searchResultsPage.getRemoteJobsPillStyle(), "rgba(89, 89, 89, 1)");
        assertTrue(searchResultsPage.getResultingNumberOfJobs().contains("jobs"));
    }
}
