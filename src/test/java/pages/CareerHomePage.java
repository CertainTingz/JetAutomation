package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CareerHomePage {

    private final Page page;

    // Declaring locators
    private final Locator buttonSearch;
    private final Locator textBoxSearch;
    //private final Locator labelRefineYourSearch;
    private final Locator dropdownOptionSales;
    private final Locator buttonAcceptCookies;



    // Constructor for initialising and assigning objects
    public CareerHomePage(Page page) {
        this.page = page;

        this.buttonSearch = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true));
        this.textBoxSearch = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Search for job title"));
        this.dropdownOptionSales = page.getByLabel("Job Categories").getByText("Sales");
        this.buttonAcceptCookies = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept all"));
    }

    public void addSearchValue(String searchValue) {
        textBoxSearch.fill(searchValue);
    }

    public void clickCareerPageSearchButton() {
        //System.out.println("clickCareerPageSearchButton");
        buttonSearch.click();

    }

    public void clickCareerPageCookieButton() {
        buttonAcceptCookies.click();
    }

    public void clickCareerHomeSearchDropdown_Sales(){
        dropdownOptionSales.click();

    }

    public void clickTextBoxSearch() {
        textBoxSearch.click();
    }

}

