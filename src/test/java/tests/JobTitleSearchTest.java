package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerFilterPage;
import pages.CareerHomePage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JobTitleSearchTest extends BaseTest {

    @Test
    public void searchJobTitleTest() {

        CareerHomePage careerHomePage = new CareerHomePage(page);
        CareerFilterPage careerFilterPage = new CareerFilterPage(page);


        //test.info("Navigating to Login Page");
        page.navigate("https://careers.justeattakeaway.com/global/en/home");


        //test.info("Entering Credentials");
        careerHomePage.addSearchValue("Test");

        //test.info("Submitting Login Form");
        careerHomePage.clickCareerPageSearchButton();

        List<String> locations = careerFilterPage.getAllLocations();
        Set<String> uniqueLocations = new HashSet<>(locations);
        System.out.println("locations: " + uniqueLocations.size());
        System.out.println(uniqueLocations);


        Assert.assertTrue(locations.size() > 1
        );

        // Capture the count BEFORE applying the filter
        int initialCount = careerFilterPage.getCareerSearchLabelResultCount();


        // Filter results for Country: Netherlands
        careerFilterPage.clickCareerFilterCountry();
        careerFilterPage.clickCareerFilterCountry_Netherlands();

        // We wait until the count displayed on the page is different from the initial count
        page.waitForCondition(() -> {
            int currentCount = careerFilterPage.getCareerSearchLabelResultCount();
            return currentCount != initialCount;
        });


        List<String> netherlandsLocation = careerFilterPage.getAllLocations();
        System.out.println("Netherlands locations only: " + netherlandsLocation.size());
        System.out.println(netherlandsLocation);


        // Correctly checks that every returned result contains “Netherlands.”
        for (String location : netherlandsLocation) {
            Assert.assertTrue(
                    location.contains("Netherlands"),
                    "Location is in Netherlands: " + location
            );
        }

    }

}
