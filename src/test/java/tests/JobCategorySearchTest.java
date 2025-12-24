package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerFilterPage;
import pages.CareerHomePage;

import java.util.List;

public class JobCategorySearchTest extends BaseTest {


    @Test
    public void searchJobCategoryTest() {

        CareerHomePage careerHomePage = new CareerHomePage(page);
        CareerFilterPage careerFilterPage = new CareerFilterPage(page);

        test.info("Navigating to Landing Page");
        page.navigate(prop.getProperty("webURL"));

        // Click on the search bar ("Search for Job Title")
        careerHomePage.clickCareerHomeSearchBar();

        // Click on Option Sales
        test.info("Select 'Sales' among Job Categories");
        careerHomePage.clickCareerHomeSearchDropdown_Sales();

        //  Scroll to label: "Refine your search"
        careerFilterPage.scrollToCareerFilterRefineYourSearchLabel();

        // Check if the Sales Checkbox is checked :
        test.info("Verifying Category 'Sales' is selected");
        Assert.assertTrue(
                careerFilterPage.isCareerFilterSelectCategory_SalesChecked());


       int labelCount = careerFilterPage.getCareerSearchLabelResultCount();
       int actualJobCount = careerFilterPage.getAllJobs().size();


        // Check if the number of jobs matches that provided in the list
        test.info("Verifying 'Sales' results number is matching");
        Assert.assertEquals(labelCount, actualJobCount);

        test.pass("Verified 'Sales' results number is matching. Count on Label: "+labelCount+", Count in DOM Element: "+actualJobCount+".");

        // Select Country Filter
        careerFilterPage.clickCareerFilterCountry();


        // Capture the count BEFORE applying the filter
        int initialCount = careerFilterPage.getCareerSearchLabelResultCount();

        // Filter for Germany
        test.info("Refining search from the left panel to the Country 'Germany'");
        careerFilterPage.clickCareerFilterCountry_Germany();


        // We wait until the count displayed on the page is different from the initial count
        page.waitForCondition(() -> {
            int currentCount = careerFilterPage.getCareerSearchLabelResultCount();
            return currentCount != initialCount;
        });



        // Verify if Germany Search result number is matching and Category is Sales for all entries.
        test.info("Verifying the number of the search results is matching");

        int categoryLabelCount = careerFilterPage.getCareerSearchLabelResultCount();
        System.out.println("This is the count from the label "+categoryLabelCount);

        List<String> salesJobCategory = careerFilterPage.getAllJobCategories();
        System.out.println("This is the count from the DOM " + salesJobCategory.size());

        // Actual check here
        Assert.assertEquals(categoryLabelCount, salesJobCategory.size());

        test.pass("Verified the number of the search results is matching. Count on Label: "+categoryLabelCount+", Count in DOM Element: "+salesJobCategory.size()+".");


        // Correctly checks that every returned result contains “Sales”
        test.info("Verifying the search results category is 'Sales' on all results");
        for (String category :salesJobCategory ) {
            Assert.assertTrue( category.contains("Sales"));
        }
        //page.pause();

        test.pass("Verified the search results category is 'Sales' on all results");

    }


}
