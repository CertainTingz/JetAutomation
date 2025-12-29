package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerFilterSection;
import pages.CareerHomePage;


public class ClearAllFiltersTest extends BaseTest {

    @Test
    public void clearAllFiltersTest() {

        CareerHomePage careerHomePage = new CareerHomePage(page);
        CareerFilterSection careerFilterSection = new CareerFilterSection(page);

        test.info("Navigating to Landing Page");
        page.navigate(prop.getProperty("webURL"));

        // Accept cookies
        careerHomePage.clickButtonAcceptCookies();


        // Click on the search bar ("Search for Job Title")
        careerHomePage.clickTextBoxSearch();


        // Click on Option: Data and Analytics
        test.info("Selecting 'Data & Analytics' among Job Categories");
        careerHomePage.clickDropdownOptionDataAndAnalytics();

        //  Scroll to label: "Refine your search"
        careerFilterSection.scrollToCareerFilterRefineYourSearchLabel();

        // Click on Country Dropdown
        test.info("Clicking on Country for option 'United Kingdom'");
        careerFilterSection.clickButtonCountry();

        // Click the United Kingdom
        careerFilterSection.clickCheckBoxCountry_UnitedKingdom();
        careerFilterSection.waitForLoadToComplete();

        // Click City Dropdown
        test.info("Clicking on City for option 'London'");
        careerFilterSection.clickButtonCity();

        // Click City: London
        careerFilterSection.clickCheckBoxCity_London();
        careerFilterSection.waitForLoadToComplete();

        // Click Type Dropdown
        test.info("Clicking on Type for option 'Full Time'");
        careerFilterSection.clickButtonType();

        // Click Full Time
        careerFilterSection.clickCheckBoxType_FullTime();
        careerFilterSection.waitForLoadToComplete();

        // Check all filters are checked.


        // Click the "Clear All Filters" button
        careerFilterSection.clickClearAllButton();

        // Verify if the button is nolonger available confirming all the filter are removed
        test.info("Verifying if the 'Clear All Button' is nolonger available");
        Assert.assertFalse(careerFilterSection.isClearAllFiltersButtonVisible());

        test.pass("Verified that the 'Clear All Button' is nolonger available");

        //page.pause();


    }
}
