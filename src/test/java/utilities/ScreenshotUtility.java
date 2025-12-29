package utilities;

import com.microsoft.playwright.Page;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {

    public static String takeScreenshot(Page page, String testName){

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // Relative to report file
        String relativePath = "screenshots/" + testName + "_" + timeStamp + ".png";

        // Absolute path for saving
        Path absolutePath = Paths.get(
                System.getProperty("user.dir"),
                "test_output",
                relativePath
        );

        try {
            Files.createDirectories(absolutePath.getParent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(absolutePath)
                .setFullPage(true));

        return relativePath.replace("\\", "/");
    }
}