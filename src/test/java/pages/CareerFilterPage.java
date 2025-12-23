package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import utilities.PaginationHelper;

import java.util.List;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CareerFilterPage {

    private final Page page;
    private final PaginationHelper paginator;

    // Declaring locators
    private final Locator careerFilterCountry;
    private final Locator careerFilterSelectCountry_Netherlands;
    private final Locator careerFilterSelectCountry_Germany;
    private final Locator careerFilterSelectCategory_Sales;
    private final Locator careerSearchJobLocationResult;
    private final Locator careerSearchLabelResultCount;
    private final Locator careerSearchJobList;
    private final Locator careerFilterRefineYourSearchLabel;
    private final Locator careerFilterNextPageArrow;
    private final Locator careerFilterPreviousPageArrow;
    private final Locator careerSearchJobCategory;


    public CareerFilterPage(Page page) {

        // Constructor for initialising and assigning objects
        this.page = page;
        this.paginator = new PaginationHelper(page);

        this.careerFilterCountry = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Country"));
        this.careerFilterSelectCountry_Netherlands = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("netherlands", Pattern.CASE_INSENSITIVE)));
        this.careerFilterSelectCountry_Germany = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("germany", Pattern.CASE_INSENSITIVE)));
        this.careerFilterRefineYourSearchLabel = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Refine your search"));
        this.careerFilterSelectCategory_Sales = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("sales", Pattern.CASE_INSENSITIVE)));
        this.careerFilterNextPageArrow = page.locator("a[data-ph-at-id='pagination-next-link']");
        this.careerFilterPreviousPageArrow = page.locator("a[data-ph-at-id='pagination-prev-link']");
        //this.careerSearchJobLocationResult = page.locator("[data-ph-at-id='job-location'] div[role='text']");
        this.careerSearchJobLocationResult = page.locator("div[data-ph-at-id='job-location'] div[role='text']");
        this.careerSearchJobList = page.locator("[data-ph-at-id='jobs-list']");
        this.careerSearchLabelResultCount = page.locator("[data-ph-at-id='search-page-top-job-count']");
        this.careerSearchJobCategory = page.locator("[data-ph-at-id='job-category'] div[role='text']");// important

    }


    public void clickCareerFilterCountry() {
        careerFilterCountry.click();

    }

    public void clickCareerFilterCountry_Netherlands() {
        careerFilterSelectCountry_Netherlands.check();

    }

    public void clickCareerFilterCountry_Germany() {
        careerFilterSelectCountry_Germany.check();
    }
/*
    // Extract unique locations from the results
    public Set<String> getUniqueLocations() {

        assertThat(careerSearchJobLocationResult.first()).isVisible();
        // Extract locations
        List<String> locations = careerSearchJobLocationResult.allTextContents();
        return new HashSet<>(locations);
    }

 */
/*
  public List<String> getAllLocations() {
      careerSearchJobLocationResult.first().waitFor();
   return paginator.collectTextAcrossPages(careerSearchJobLocationResult, careerFilterNextPageArrow);
    }

 */
/*
    public List<String> getAllLocations() {
        // Ensure we start from first page

        // Wait for results to load before collecting
        //careerSearchJobLocationResult.first().waitFor(new Locator.WaitForOptions()
               // .setState(WaitForSelectorState.VISIBLE));

        return paginator.collectTextAcrossPages(careerSearchJobLocationResult, careerFilterNextPageArrow);
    }

 */


    public List<String> getAllJobs() {
        //System.out.println("getAllJobs() ran successfully");
        return paginator.collectTextAcrossPages(careerSearchJobList, careerFilterNextPageArrow);
    }

    public List<String> getAllJobCategories() {
        careerSearchJobLocationResult.first().waitFor();
        return paginator.collectTextAcrossPages(careerSearchJobCategory, careerFilterNextPageArrow);
    }


    public void scrollToCareerFilterRefineYourSearchLabel() {
        // Scrolling to label
        careerFilterRefineYourSearchLabel.scrollIntoViewIfNeeded();
    }

    public boolean isCareerFilterSelectCategory_SalesChecked() {
        careerFilterSelectCategory_Sales.waitFor(); // Necessary for isChecked().
        return careerFilterSelectCategory_Sales.isChecked();

    }

/*
    // Extract the total number from the counter text
    public int getCareerSearchLabelResultCount() {
        String text = careerSearchLabelResultCount.innerText();
        //System.out.println(text);
        return Integer.parseInt(text);
    }

 */
    // ... inside CareerFilterPage.java ...

    public List<String> getAllLocations() {
        // Ensure the results are visible before starting to scrape/paginate
        careerSearchJobLocationResult.first().waitFor();
        return paginator.collectTextAcrossPages(careerSearchJobLocationResult, careerFilterNextPageArrow);
    }

    public int getCareerSearchLabelResultCount() {
        // Use innerText() and regex to extract only the numbers (e.g., "150 results" -> 150)
        String text = careerSearchLabelResultCount.innerText();
        String numericValue = text.replaceAll("[^0-9]", "");
        return numericValue.isEmpty() ? 0 : Integer.parseInt(numericValue);
    }


   /*
    public int getTotalJobCount() {
      return paginator.countAcrossPages(careerSearchJobList, careerFilterNextPageArrow);
    }

    */

}



