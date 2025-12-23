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

        // Test.info("Navigating to Login Page");
        page.navigate("https://careers.justeattakeaway.com/global/en/home");

        // Click on the search bar ("Search for Job Title")
        careerHomePage.clickCareerHomeSearchBar();

        // Click on Option Sales
        careerHomePage.clickCareerHomeSearchDropdown_Sales();

        //  Scroll to label: "Refine your search"
        careerFilterPage.scrollToCareerFilterRefineYourSearchLabel();

        // Check if the Sales Checkbox is checked :
        Assert.assertTrue(
                careerFilterPage.isCareerFilterSelectCategory_SalesChecked());


       int labelCount = careerFilterPage.getCareerSearchLabelResultCount();
       int actualJobCount = careerFilterPage.getAllJobs().size();

        //System.out.println("Job Category Search Result: " + labelCount + " Job Count: " + actualJobCount);

        // Check if the number of jobs matches that provided in the list
        Assert.assertEquals(labelCount, actualJobCount);

        // Select Country Filter
        careerFilterPage.clickCareerFilterCountry();


        // Capture the count BEFORE applying the filter
        int initialCount = careerFilterPage.getCareerSearchLabelResultCount();

        // Filter for Germany
        careerFilterPage.clickCareerFilterCountry_Germany();


        // We wait until the count displayed on the page is different from the initial count
        page.waitForCondition(() -> {
            int currentCount = careerFilterPage.getCareerSearchLabelResultCount();
            return currentCount != initialCount;
        });



        // Verify if Germany Search result number is matching and Category is Sales for all
        int categoryLabelCount = careerFilterPage.getCareerSearchLabelResultCount();
        System.out.println("This is the count from the label "+categoryLabelCount);

        List<String> salesJobCategory = careerFilterPage.getAllJobCategories();
        System.out.println("This is the count from the DOM " + salesJobCategory.size());

        // Actual check here
        Assert.assertEquals(categoryLabelCount, salesJobCategory.size());




        // Correctly checks that every returned result contains “Sales”
        for (String category :salesJobCategory ) {
            Assert.assertTrue( category.contains("Sales"));
        }
        //page.pause();

    }


}
