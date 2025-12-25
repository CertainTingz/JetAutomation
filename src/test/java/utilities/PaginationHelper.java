package utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.ArrayList;
import java.util.List;

public class PaginationHelper {

    private final Page page;

    public PaginationHelper(Page page) {
        this.page = page;
    }




    public List<String> collectTextAcrossPages(Locator itemLocator, Locator nextArrow) {
        List<String> results = new ArrayList<>();

        while (true) {
            // Collect current page
            results.addAll(itemLocator.allInnerTexts());

            // If next arrow is gone, stop
            if (nextArrow.count() == 0 || !nextArrow.isVisible()) break;

            nextArrow.click();


            // Now wait for new items to appear
            itemLocator.first().waitFor();
        }

        return results;
    }
}





