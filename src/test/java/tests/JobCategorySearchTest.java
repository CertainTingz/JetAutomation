package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerFilterPage;
import pages.CareerHomePage;

public class JobCategorySearchTest extends BaseTest {



    @Test
    public void searchJobCategoryTest() {

        CareerHomePage careerHomePage = new CareerHomePage(page);
        CareerFilterPage careerFilterPage = new CareerFilterPage(page);

        // Test.info("Navigating to Login Page");
        page.navigate("https://careers.justeattakeaway.com/global/en/home");

        // Click on search bar ("Search for Job Title")
        careerHomePage.clickCareerHomeSearchBar();

        // Click on Option Sales
        careerHomePage.clickCareerHomeSearchDropdown_Sales();

        //  Scroll to label : "Refine your search"
        careerFilterPage.scrollToCareerFilterRefineYourSearchLabel();

        // Check if Sales Checkbox is checked :
        Assert.assertTrue(
               careerFilterPage.isCareerFilterSelectCategory_SalesChecked());



        int labelCount = careerFilterPage.getCareerSearchLabelResultCount();
        int actualJobCount = careerFilterPage.getTotalJobCount();

        // Check if the number of jobs matches that provided in the list
        Assert.assertEquals(labelCount, actualJobCount);

        System.out.println("this is the count from label : "+ labelCount);
        System.out.println("this is the count from element : "+ actualJobCount);



        //page.pause();


    }




}
