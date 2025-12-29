package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerFilterSection;
import pages.CareerHomePage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class NoMatchJobSearchTest extends BaseTest {

    @Test
    public void noMatchJobSearchTest() {

        CareerHomePage careerHomePage = new CareerHomePage(page);
        CareerFilterSection careerFilterSection = new CareerFilterSection(page);

        // Navigating to Landing page
        test.info("Navigating to Landing Page");
        page.navigate(prop.getProperty("webURL"));

        // Accept cookies
        careerHomePage.clickButtonAcceptCookies();

        // Entering the search term
        test.info("Entering the search term : 'NoMatch'");
        careerHomePage.addSearchValue(prop.getProperty("noJob"));

        // Submitting the search term
        test.info("Submitting search query");
        careerHomePage.clickButtonSearch();


        // Verifying if results contain results from multiple location
        test.info("Verifying that system handles a none matching job search");


        Assert.assertEquals(careerFilterSection.expectedNoMatchMessage(), careerFilterSection.actualNoMatchMessage(prop.getProperty("noJob")));
        //page.pause();
        test.pass("Verified that system handles a none matching job search. Expecting: "+careerFilterSection.expectedNoMatchMessage()+". Found: "+careerFilterSection.actualNoMatchMessage(prop.getProperty("noJob"))+".");



    }
}
