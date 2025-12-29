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
    private final Locator buttonCity;
    private final Locator buttonType;
    private final Locator checkBoxCountry_Netherlands;
    private final Locator checkBoxCountry_Germany;
    private final Locator checkBoxCountry_UnitedKingdom;
    private final Locator checkBoxCategory_Sales;
    private final Locator checkBoxCity_London;
    private final Locator checkBoxType_FullTime;
    private final Locator jobLocation;
    private final Locator labelResultCount;
    private final Locator labelRefineYourSearch;
    private final Locator nextPageArrow;
    private final Locator jobCategory;
    private final Locator refreshLoader;
    private final Locator jobId;
    private final Locator noMatchLabel;
    private final Locator clearAllButton;




    public CareerFilterSection(Page page) {

        // Constructor for initialising and assigning objects
        this.page = page;
        this.paginator = new PaginationHelper(page);

        this.buttonCountry = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Country"));
        this.buttonCity = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("City"));
        this.buttonType = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Type"));
        this.clearAllButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear all filters"));
        this.checkBoxCountry_Netherlands = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("netherlands", Pattern.CASE_INSENSITIVE)));
        this.checkBoxCountry_Germany = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("germany", Pattern.CASE_INSENSITIVE)));
        this.checkBoxCountry_UnitedKingdom = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("kingdom", Pattern.CASE_INSENSITIVE)));
        this.checkBoxCity_London = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("London", Pattern.CASE_INSENSITIVE)));
        this.checkBoxType_FullTime = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("full time", Pattern.CASE_INSENSITIVE)));
        this.labelRefineYourSearch = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Refine your search"));
        this.checkBoxCategory_Sales = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(Pattern.compile("sales", Pattern.CASE_INSENSITIVE)));
        this.noMatchLabel = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("We couldn’t find any open"));
        this.nextPageArrow = page.locator("[data-ph-at-id='pagination-next-link']");
        this.jobLocation = page.locator("[role='text'][data-ph-at-id='job-location']");
        this.labelResultCount = page.locator("[data-ph-at-id='search-page-top-job-count']");
        this.jobCategory = page.locator("[role='text'][data-ph-at-id='job-category']");
        this.refreshLoader = page.locator(".phw-spinner-border.phw-primary");
        this.jobId = page.locator("[data-ph-at-job-id-text]");


    }


    public void clickButtonCountry() {
        buttonCountry.click();
    }

    public void clickButtonCity() {
        buttonCity.click();
    }

    public void clickButtonType() {
        buttonType.click();
    }

    public void clickCheckBoxCountry_Netherlands() {
        checkBoxCountry_Netherlands.check();

    }

    public void clickCheckBoxCountry_Germany() {
        checkBoxCountry_Germany.check();
    }

    public void clickCheckBoxCountry_UnitedKingdom() {
        checkBoxCountry_UnitedKingdom.check();
    }

    public void clickCheckBoxCity_London() {
        checkBoxCity_London.check();
    }

    public void clickCheckBoxType_FullTime() {
        checkBoxType_FullTime.check();
    }

    public void clickClearAllButton() {
        clearAllButton.click();
    }




    public List<String> getAllJobCategories() {
        //jobCategory.first().waitFor();
        return paginator.collectJobDataAcrossPages(jobCategory,jobId, nextPageArrow);
    }

    public List<String> getAllLocations() {

        return paginator.collectJobDataAcrossPages(jobLocation,jobId, nextPageArrow);
    }


    public void scrollToCareerFilterRefineYourSearchLabel() {
        // Scrolling to label
        labelRefineYourSearch.scrollIntoViewIfNeeded();
    }

    public boolean isCategory_SalesChecked() {
        checkBoxCategory_Sales.waitFor(); // waitFor() Necessary for isChecked().
        return checkBoxCategory_Sales.isChecked();

    }

    public boolean isClearAllFiltersButtonVisible() {
        System.out.println("is it visible: "+ clearAllButton.isVisible());
        return clearAllButton.isVisible();

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
        if (appeared) {
            System.out.println("Loader appeared and disappeared");
        }

    }

    public String actualNoMatchMessage(String searchTerm) {
        String actualMessage = "We couldn’t find any open positions for \"" + searchTerm + "\"";
        return actualMessage;

    }

    public String expectedNoMatchMessage() {
        noMatchLabel.waitFor();
        String expectedMessage = noMatchLabel.innerText();
        return expectedMessage;

    }


}



