package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CareerHomePage {

    private final Page page;

    // Declaring locators
    private final Locator buttonSearch;
    private final Locator textBoxSearch;
    private final Locator dropdownOptionSales;
    private final Locator dropdownOptionDataAndAnalytics;
    private final Locator buttonAcceptCookies;



    // Constructor for initialising and assigning objects
    public CareerHomePage(Page page) {
        this.page = page;

        this.buttonSearch = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true));
        this.textBoxSearch = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Search for job title"));
        this.dropdownOptionSales = page.getByLabel("Job Categories").getByText("Sales");
        this.dropdownOptionDataAndAnalytics = page.getByLabel("Job Categories").getByText("Data & Analytics");
        this.buttonAcceptCookies = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept all"));
    }

    public void addSearchValue(String searchValue) {
        textBoxSearch.fill(searchValue);
    }

    public void clickButtonSearch() {
        buttonSearch.click();

    }

    public void clickButtonAcceptCookies() {
        buttonAcceptCookies.click();
    }

    public void clickDropdownOptionSales(){
        dropdownOptionSales.click();

    }

    public void clickDropdownOptionDataAndAnalytics(){
        dropdownOptionDataAndAnalytics.click();

    }

    public void clickTextBoxSearch() {
        textBoxSearch.click();
    }

}

