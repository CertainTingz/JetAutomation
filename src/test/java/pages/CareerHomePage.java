package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class CareerHomePage {

    private final Page page;

    // Declaring locators
    private final Locator careerHomeSearchButton;
    private final Locator careerHomeSearchBar;
    private final Locator careerPageRefineLabel;
    private final Locator careerPageSearchDropdown_Sales;



    // Constructor for initialising and assigning objects
    public CareerHomePage(Page page) {
        this.page = page;

        this.careerHomeSearchButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true));
        this.careerHomeSearchBar = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Search for job title"));
        this.careerPageRefineLabel = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Refine your search"));
        this.careerPageSearchDropdown_Sales = page.getByLabel("Job Categories").getByText("Sales");
    }

    public void addSearchValue(String searchValue) {
        careerHomeSearchBar.fill(searchValue);
    }

    public void clickCareerPageSearchButton() {
        System.out.println("clickCareerPageSearchButton");
        careerHomeSearchButton.click();

    }


    public boolean isCareerPageRefineLabelDisplayed() {
        careerPageRefineLabel.waitFor();
        return careerPageRefineLabel.isVisible();

    }

    public void clickCareerHomeSearchDropdown_Sales(){
        careerPageSearchDropdown_Sales.click();

    }

    public void clickCareerHomeSearchBar() {
        // Wait for network requests to complete
        page.waitForLoadState(LoadState.NETWORKIDLE);
        careerHomeSearchBar.click(); }



}
