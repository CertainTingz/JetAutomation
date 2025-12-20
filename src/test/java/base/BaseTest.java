package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;


    @BeforeMethod
    public void setUp() {

        // Playwright Setup
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge"));

        page = browser.newPage();

    }


    @AfterMethod
    public void tearDown() {
        if(browser != null) browser.close();
        if(playwright != null) playwright.close();
    }



}
