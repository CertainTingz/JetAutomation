package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerFilterSection;
import pages.CareerHomePage;

import java.util.List;

public class JobCategorySearchTest extends BaseTest {


    @Test
    public void searchJobCategoryTest() {

        CareerHomePage careerHomePage = new CareerHomePage(page);
        CareerFilterSection careerFilterSection = new CareerFilterSection(page);

        test.info("Navigating to Landing Page");
        page.navigate(prop.getProperty("webURL"));

        // Accept cookies
        careerHomePage.clickCareerPageCookieButton();


        // Click on the search bar ("Search for Job Title")
        careerHomePage.clickTextBoxSearch();
        //page.pause();

        // Click on Option Sales
        test.info("Select 'Sales' among Job Categories");
        careerHomePage.clickCareerHomeSearchDropdown_Sales();

        //  Scroll to label: "Refine your search"
        careerFilterSection.scrollToCareerFilterRefineYourSearchLabel();

        // Check if the Sales Checkbox is checked :
        test.info("Verifying Category 'Sales' is selected");
        Assert.assertTrue(
                careerFilterSection.isCareerFilterSelectCategory_SalesChecked());

        test.pass("Verified Category 'Sales' is selected");


        int labelCount = careerFilterSection.getLabelResultCount();
        int jobCountInDOM = careerFilterSection.getAllJobCategories().size();

        careerFilterSection.clickCareerFilterCountry();


        // Check if the number of jobs matches that provided in the list
        test.info("Verifying 'Sales' results number is matching");
        Assert.assertEquals(jobCountInDOM, labelCount);

        test.pass("Verified 'Sales' results number is matching. Count on Label: " + labelCount + ", Count in DOM Element: " + jobCountInDOM + ".");



        // Filter for Germany
        test.info("Refining search from the left panel to the Country 'Germany'");

        // Select Country Filter
        //careerFilterSection.clickCareerFilterCountry();
        //page.pause();
        careerFilterSection.clickCareerFilterCountry_Germany();
        careerFilterSection.waitForLoadToComplete();


        // Verify if Germany Search result number is matching and Category is Sales for all entries.
        test.info("Verifying the count of the Germany filter search results is matching");

        List<String> salesJobCategory = careerFilterSection.getAllJobCategories();

        //System.out.println(salesJobCategory);


        // Correctly checks that every returned result contains “Sales”
        test.info("Verifying the search results category is 'Sales' on all results");
        for (String category : salesJobCategory) {
            Assert.assertTrue(category.contains("Sales"));
        }

        test.pass("Verified the search results category is 'Sales' on all results");



        //System.out.println("This is the count from the DOM " + salesJobCategory.size());
        //System.out.println("Germany list of categories "+salesJobCategory);
        int salesCategoriesCount = salesJobCategory.size();
        int categoryLabelCount = careerFilterSection.getLabelResultCount();

        //System.out.println("This is the count from the label " + categoryLabelCount);


        // Actual check here
        Assert.assertEquals(salesCategoriesCount, categoryLabelCount);

        test.pass("Verified the count of the Germany filter search results is matching. Count on Label: " + categoryLabelCount + ", Count in DOM Element: " + salesCategoriesCount+ ".");



    }


}
