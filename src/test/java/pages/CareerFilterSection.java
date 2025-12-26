package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import utilities.PaginationHelper;

import java.util.List;
import java.util.regex.Pattern;


public class CareerFilterSection {

    private final Page page;
    private final PaginationHelper paginator;

    // Declaring locators
    private final Locator buttonCountry;
    private final Locator checkBoxCountry_Netherlands;
    private final Locator checkBoxCountry_Germany;
    private final Locator checkBoxCategory_Sales;
    private final Locator jobLocation;
    private final Locator labelResultCount;
    private final Locator labelRefineYourSearch;
    private final Locator nextPageArrow;
    private final Locator jobCategory;
    private final Locator refreshLoader;
    private final Locator jobList;



    public CareerFilterSection(Page page) {

        // Constructor for initialising and assigning objects
        this.page = page;
        this.paginator = new PaginationHelper(page);

        this.buttonCountry = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Country"));
        this.checkBoxCountry_Netherlands = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("netherlands", Pattern.CASE_INSENSITIVE)));
        this.checkBoxCountry_Germany = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("germany", Pattern.CASE_INSENSITIVE)));
        this.labelRefineYourSearch = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Refine your search"));
        this.checkBoxCategory_Sales = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("sales", Pattern.CASE_INSENSITIVE)));
        this.nextPageArrow = page.locator("[data-ph-at-id='pagination-next-link']");
        this.jobLocation = page.locator("[role='text'][data-ph-at-id='job-location']");
        this.labelResultCount = page.locator("[data-ph-at-id='search-page-top-job-count']");
        this.jobCategory = page.locator("[role='text'][data-ph-at-id='job-category']");
        this.refreshLoader = page.locator(".phw-spinner-border.phw-primary");
        this.jobList = page.locator("[data-ph-at-id='job-list']");

    }


    public void clickCareerFilterCountry() {
        //buttonCountry.scrollIntoViewIfNeeded();
        buttonCountry.click();
        //System.out.println("Clicked Country"+1);

    }

    public void clickCareerFilterCountry_Netherlands() {
        checkBoxCountry_Netherlands.check();

    }

    public void clickCareerFilterCountry_Germany() {
        //checkBoxCountry_Germany.scrollIntoViewIfNeeded();
        checkBoxCountry_Germany.check();
    }

    public List<String> getAllJobCategories() {
        jobCategory.first().waitFor();
        //waitForLoadToComplete();
        return paginator.collectTextAcrossPages(jobCategory, nextPageArrow);
    }


    public List<String> getAllLocations() {
        // Ensure the results are visible before action
        jobLocation.first().waitFor();
        //waitForLoadToComplete();
        return paginator.collectTextAcrossPages(jobLocation, nextPageArrow);
    }


//    // Trial fix
//    public List<String> getAllJobCategoriesTrial() {
//        jobCategory.first().waitFor();
//        return paginator.collectJobCategoriesAcrossPages(jobCategory, nextPageArrow);
//    }






    public void scrollToCareerFilterRefineYourSearchLabel() {
        // Scrolling to label
        labelRefineYourSearch.scrollIntoViewIfNeeded();
    }

    public boolean isCareerFilterSelectCategory_SalesChecked() {
        checkBoxCategory_Sales.waitFor(); // waitFor() Necessary for isChecked().
        return checkBoxCategory_Sales.isChecked();

    }



    public int getLabelResultCount() {
        return Integer.parseInt(labelResultCount.innerText().trim());

    }

    public void waitForLoadToComplete() {

        System.out.println("Spinner is visible? :" + refreshLoader.isVisible());
        if (refreshLoader.isVisible()) {
            refreshLoader.waitFor(
                    new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.HIDDEN)
            );
        }




        // Wait for the spinner to disappear before reading results
        //refreshLoader.waitFor(new Locator.WaitForOptions()
           //     .setState(WaitForSelectorState.HIDDEN));
    }

}



