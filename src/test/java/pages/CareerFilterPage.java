package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utilities.PaginationHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CareerFilterPage {

    private final Page page;
    private final PaginationHelper paginator;

    // Declaring locators
    private final Locator careerFilterCountry;
    private final Locator careerFilterSelectCountry_Netherlands;
    private final Locator careerFilterSelectCategory_Sales;
    private final Locator careerSearchJobLocationResult;
    private final Locator careerSearchLabelResultCount;
    private final Locator careerSearchElementResultCount;
    private final Locator careerFilterRefineYourSearchLabel;
    private final Locator careerFilterNextPageArrow;






    public CareerFilterPage(Page page) {

        // Constructor for initialising and assigning objects
        this.page = page;
        this.paginator = new PaginationHelper(page);

        this.careerFilterCountry = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Country"));
        this.careerFilterSelectCountry_Netherlands = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("netherlands", Pattern.CASE_INSENSITIVE)));
        this.careerFilterRefineYourSearchLabel = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Refine your search"));
        this.careerFilterSelectCategory_Sales = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("sales", Pattern.CASE_INSENSITIVE)));
        this.careerFilterNextPageArrow = page.locator("a[data-ph-at-id='pagination-next-link']");
        this.careerSearchJobLocationResult = page.locator("[data-ph-at-id='job-location'] div[role='text']");
        this.careerSearchElementResultCount = page.locator("[data-ph-at-id='jobs-list']");  //important
        this.careerSearchLabelResultCount = page.locator("[data-ph-at-id='search-page-top-job-count']"); // important

    }


    public void clickCareerFilterCountry() {
        careerFilterCountry.click();
    }

    public void clickCareerFilterCountry_Netherlands() {
        careerFilterSelectCountry_Netherlands.check();
        // Wait for first result to contain "Netherlands"
        // Using Pattern for case-insensitive matching
        assertThat(careerSearchJobLocationResult.last())
                .containsText(Pattern.compile("Netherlands", Pattern.CASE_INSENSITIVE));
    }

    // Extract unique locations from the results
    public Set<String> getUniqueLocations() {

        // Wait for the last result to be visible with content
        assertThat(careerSearchJobLocationResult.last()).isVisible();

        // Extract locations
        List<String> locations = careerSearchJobLocationResult.allTextContents();

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


    // Extract the total number from the counter text
    public int getCareerSearchLabelResultCount() {
        String text = careerSearchLabelResultCount.innerText();


        //System.out.println(text);
        return Integer.parseInt(text);
    }


    public int getTotalJobCount() {
      return paginator.countAcrossPages(careerSearchElementResultCount, careerFilterNextPageArrow);
    }



}



