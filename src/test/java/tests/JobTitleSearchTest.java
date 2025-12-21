package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerFilterPage;
import pages.CareerHomePage;

import java.util.Set;

public class JobTitleSearchTest extends BaseTest {

    @Test
    public void searchJobTitleTest() {

        CareerHomePage careerHomePage = new CareerHomePage(page);
        CareerFilterPage careerFilterPage = new CareerFilterPage(page);

        //Handling Cookie Notification
        if (page.isVisible("button:has-text('Accept all')")){
            page.click("button:has-text('Accept all')");
        }
        else{
            System.out.println("cookie page not visible");

        }

        //test.info("Navigating to Login Page");
        page.navigate("https://careers.justeattakeaway.com/global/en/home");


        //test.info("Entering Credentials");
        careerHomePage.addSearchValue("Test");

        //test.info("Submitting Login Form");
        careerHomePage.clickCareerPageSearchButton();



        //Assert.assertEquals(homePage.getHomepageLabelText(), "Dashboard");
        //Assert.assertTrue(careerHomePage.isCareerPageRefineLabelDisplayed());

        Set<String> locations = careerFilterPage.getUniqueLocations();
        System.out.println("locations: " + locations.size());
        System.out.println(locations);


        Assert.assertTrue(
                locations.size() > 1
        );


        // Filter results for Country : Netherlands
        careerFilterPage.clickCareerFilterCountry();
        careerFilterPage.clickCareerFilterCountry_Netherlands();


        Set<String> netherlandsLocation = careerFilterPage.getUniqueLocations();
        System.out.println("Netherlands locations only: " + netherlandsLocation.size());
        System.out.println(netherlandsLocation);


        for (String location : netherlandsLocation) {
            Assert.assertTrue(
                    location.contains("Netherlands"),
                    "Location is not in Netherlands: " + location
            );
        }




    }





}
