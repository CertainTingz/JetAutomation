package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CareerFilterPage {

    private final Page page;

    // Declaring locators
    private final Locator careerFilterCountry;
    private final Locator careerFilterSelectCountry_Netherlands;
    private final Locator careerFilterSelectCategory_Sales;
    private final Locator careerSearchResult;
    private final Locator careerFilterRefineYourSearchLabel;




    public CareerFilterPage(Page page) {

        // Constructor for initialising and assigning objects
        this.page = page;

        this.careerFilterCountry = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Country"));
        this.careerFilterSelectCountry_Netherlands = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("netherlands", Pattern.CASE_INSENSITIVE)));
        this.careerFilterRefineYourSearchLabel = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Refine your search"));
        this.careerFilterSelectCategory_Sales = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("sales", Pattern.CASE_INSENSITIVE)));
        this.careerSearchResult = page.locator("[data-ph-at-id='job-location'] div[role='text']");
    }


    public void clickCareerFilterCountry() {
        careerFilterCountry.click();
    }

    public void clickCareerFilterCountry_Netherlands() {
        careerFilterSelectCountry_Netherlands.check();
        // Wait for first result to contain "Netherlands"
        // Using Pattern for case-insensitive matching
        assertThat(careerSearchResult.last())
                .containsText(Pattern.compile("Netherlands", Pattern.CASE_INSENSITIVE));
    }

    // Extract unique locations from the results
    public Set<String> getUniqueLocations() {

        // Wait for the last result to be visible with content
        assertThat(careerSearchResult.last()).isVisible();

        // Extract locations
        List<String> locations = careerSearchResult.allTextContents();

        return new HashSet<>(locations);
    }

    public void scrollToCareerFilterRefineYourSearchLabel(){
        // Scrolling to label
        careerFilterRefineYourSearchLabel.scrollIntoViewIfNeeded();
    }

    public boolean isCareerFilterSelectCategory_SalesChecked() {
        careerFilterSelectCategory_Sales.waitFor();
        return careerFilterSelectCategory_Sales.isChecked();

    }

}



