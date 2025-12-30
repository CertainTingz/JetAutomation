package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utilities.ExtentManager;
import utilities.ScreenshotUtility;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;


public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected Properties prop;


    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(Method method,String br) throws IOException {


        //Loading a config.properties
        FileReader file = new FileReader("./src//test//resources//config.properties");
        prop = new Properties();
        prop.load(file);

        //Reporting
        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());



// Playwright Setup with browser selection
        playwright = Playwright.create();

// Selecting a browser based on the one set in crossBrowserTesting XML file
        switch (br.toLowerCase()) {
            case "chrome":
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                                .setChannel("chrome")
                );
                break;

            case "chromium":
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                );
                break;

            case "firefox":
                browser = playwright.firefox().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(true)
                );
                break;

            case "safari":
                browser = playwright.webkit().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                );
                break;

            case "edge":
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                                .setChannel("msedge")
                );
                break;

            default:
                test.fail("Invalid browser name: " + br);
                playwright.close();
                return;
        }

        page = browser.newPage();

    }

    @AfterMethod
  public void tearDown(ITestResult testResult) {


        //Generate Report with Screenshots.

        if(testResult.getStatus() == ITestResult.FAILURE) {
            test.fail(testResult.getThrowable());

            // Capturing and Attaching Screenshot
            String screenshotPath = ScreenshotUtility.takeScreenshot(page,testResult.getName());
            //System.out.println("Path Here Please: " + screenshotPath);

            test.addScreenCaptureFromPath(screenshotPath, "Screenshot");

        }else if(testResult.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else {
            test.skip("Test Skipped");
        }
        extent.flush();

        if(page != null) page.close();
        if(browser != null) browser.close();
        if(playwright != null) playwright.close();

    }
}
