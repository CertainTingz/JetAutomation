package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class CareerFilterPage {

    private final Page page;

    // Declaring locators
    private final Locator careerFilterCountry;
    private final Locator careerFilterSelectCountry_Netherlands;
    private final Locator careerSearchResult;
    //private final Locator careerCountrySearchResult;



    public CareerFilterPage(Page page) {

        // Constructor for initialising and assigning objects
        this.page = page;

        this.careerFilterCountry = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Country"));
        this.careerFilterSelectCountry_Netherlands = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("netherlands", Pattern.CASE_INSENSITIVE)));
        this.careerSearchResult = page.locator("[data-ph-at-id='job-location'] div[role='text']");
        //this.careerCountrySearchResult =page.locator("[data-ph-at-id='job-location'] div[role='text']");

    }


    public void clickCareerFilterCountry() {
        careerFilterCountry.click();
    }

    public void clickCareerFilterCountry_Netherlands() {
        careerFilterSelectCountry_Netherlands.check();
    }

    // Extract unique locations from the results
    public Set<String> getUniqueLocations() {

        List<String> locations = careerSearchResult.allTextContents();
        return new HashSet<>(locations);


    }

}



