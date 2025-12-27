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
    private final Locator jobId;



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
        this.jobId = page.locator("[data-ph-at-job-id-text]");

    }


    public void clickButtonCountry() {
        //buttonCountry.scrollIntoViewIfNeeded();
        buttonCountry.click();


    }

    public void clickCheckBoxCountry_Netherlands() {
        checkBoxCountry_Netherlands.check();

    }

    public void clickCheckBoxCountry_Germany() {

        checkBoxCountry_Germany.check();
    }

    public List<String> getAllJobCategories() {
        jobCategory.first().waitFor();
        return paginator.collectJobDataAcrossPages(jobCategory,jobId, nextPageArrow);
    }


    public List<String> getAllLocations() {
        // Ensure the results are visible before action
        //jobLocation.first().waitFor();
        return paginator.collectJobDataAcrossPages(jobLocation,jobId, nextPageArrow);
    }


    public void scrollToCareerFilterRefineYourSearchLabel() {
        // Scrolling to label
        labelRefineYourSearch.scrollIntoViewIfNeeded();
    }

    public boolean isCareerFilterSelectCategory_SalesChecked() {
        //checkBoxCategory_Sales.waitFor(); // waitFor() Necessary for isChecked().
        return checkBoxCategory_Sales.isChecked();

    }



    public int getLabelResultCount() {
        return Integer.parseInt(labelResultCount.innerText().trim());

    }

    public void waitForLoadToComplete() {

        boolean appeared = refreshLoader.isVisible(); // for logging

        refreshLoader.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN)
        );
//        if (appeared) {
//            System.out.println("Loader appeared and disappeared");
//        }

    }


}



