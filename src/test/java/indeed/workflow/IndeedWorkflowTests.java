package indeed.workflow;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.CompanyPageOnIndeed;
import pages.SearchResultsPageOnIndeed;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class IndeedWorkflowTests extends BaseTests {
    private SearchResultsPageOnIndeed searchResultsPage;

    @Test(priority = 1)
    public void searchIndeedJobsTest() {
        String jobTitle = "Quality Engineer";
        String jobLocation = "Virginia, United States";
        startHomePageSearch("https://www.indeed.com/jobs", jobTitle, jobLocation);
        // setSearchToRemoteJobs();
        setSearchToPostedByEmployer();
        disableObstructingElement();

        while (searchResultsPage.isPaginationNextVisible()) {
            extractCompaniesData(jobTitle, jobLocation);
            searchResultsPage.getToNextResultsPage();
        }
    }
    private void extractCompaniesData(String jobTitle, String jobLocation) {
        int resultsSize = searchResultsPage.getResultsSize();
        for (int i = 0; i < resultsSize-1; i++) {
            if (i == 5 || i == 11) continue;
            searchResultsPage.scrollThePage();
            CompanyPageOnIndeed companyPage = searchResultsPage.clickListItemCompanyLink(i);
            if (companyPage == null) continue;
            getWindowManager().switchToNewTab();
            companyPage.storeCompanyInfo();
            companyPage.closePage();
            getWindowManager().switchToTab(jobTitle + " Jobs, Employment in "+ jobLocation +" | Indeed.com");
        }
    }
    private void startHomePageSearch(String jobSearchUrl, String jobTitle, String jobLocation) {
        homePageOnIndeed.populateWhatField(jobTitle);
        homePageOnIndeed.populateWhereField(jobLocation);
        searchResultsPage = homePageOnIndeed.clickFindJobs();
        assertTrue(searchResultsPage.getCurrentUrl().contains(jobSearchUrl));
    }
    private void setSearchToRemoteJobs() {
        searchResultsPage.setRemoteJobs();
        assertEquals(searchResultsPage.getRemoteJobsPillStyle(), "rgba(89, 89, 89, 1)");
        assertTrue(searchResultsPage.getResultingNumberOfJobs().contains("jobs"));
    }

    private void setSearchToPostedByEmployer() {
        searchResultsPage.setPostedByEmployer();
        assertEquals(searchResultsPage.getRemoteJobsPillStyle(), "rgba(89, 89, 89, 1)");
        assertTrue(searchResultsPage.getResultingNumberOfJobs().contains("jobs"));
    }

    private void disableObstructingElement(){
        searchResultsPage.setAnnoyingElementToHidden();
        assertTrue(!searchResultsPage.isAnnoyingElementVisible());
    }
}
