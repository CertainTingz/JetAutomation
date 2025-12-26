package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerFilterSection;
import pages.CareerHomePage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JobTitleSearchTest extends BaseTest {

    @Test
    public void searchJobTitleTest() {

        CareerHomePage careerHomePage = new CareerHomePage(page);
        CareerFilterSection careerFilterSection = new CareerFilterSection(page);

        // Navigating to Landing page
        test.info("Navigating to Landing Page");
        page.navigate(prop.getProperty("webURL"));

        // Accept cookies
        careerHomePage.clickCareerPageCookieButton();

        // Entering the search term
        test.info("Entering the search term : 'Test'");
        careerHomePage.addSearchValue(prop.getProperty("jobTitle"));

        // Submitting the search term
        test.info("Submitting search query");
        careerHomePage.clickCareerPageSearchButton();

        // Scroll to Filtering Object
        careerFilterSection.scrollToCareerFilterRefineYourSearchLabel();

        // Verifying if results contain results from multiple location
        test.info("Verifying that the search contains results from multiple locations");
        List<String> locations = careerFilterSection.getAllLocations();
        Set<String> uniqueLocations = new HashSet<>(locations);


        Assert.assertTrue(uniqueLocations.size() > 1,
                "Expected multiple locations but found: " + uniqueLocations.size());

        test.pass("Verified that the search contains results from multiple unique locations(" + uniqueLocations.size() + ")");


        test.info("Verifying the search results’ location is the Netherlands only");
        careerFilterSection.clickCareerFilterCountry();
        careerFilterSection.clickCareerFilterCountry_Netherlands();

        careerFilterSection.waitForLoadToComplete();


        // Collecting Netherlands jobs information.
        List<String> netherlandsJobs = careerFilterSection.getAllLocations();
        //System.out.println("This is the Netherlands jobs " + netherlandsJobs);

        int netherlandsJobsLabelCount = careerFilterSection.getLabelResultCount();
        int netherlandsJobsDOMCount = netherlandsJobs.size();


        // Verifying if the Netherlands jobs count match before checking if results are for the Netherlands only
        test.info("Verifying first if the Netherlands jobs count match before checking if results are for the Netherlands only");
        Assert.assertEquals(netherlandsJobsDOMCount, netherlandsJobsLabelCount);
        test.pass("Verified the count of the Netherlands filter search results is matching. Count on Label: " + netherlandsJobsLabelCount + ", Count in DOM Element: " + netherlandsJobsDOMCount + ".");


        //System.out.println("Netherlands locations only: " + netherlandsJobs.size());
        //System.out.println(netherlandsJobs);


        // Correctly checks that every returned result contains “Netherlands.”
        for (String location : netherlandsJobs) {
            Assert.assertTrue(
                    location.contains("Netherlands"),
                    "Expected Netherlands location but got: " + location
            );
        }
        test.pass("Verified the search results’ location is the Netherlands only");
    }

}
