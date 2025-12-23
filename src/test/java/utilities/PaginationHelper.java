package utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.ArrayList;
import java.util.List;

public class PaginationHelper {

    private final Page page;

    public PaginationHelper(Page page) {
        this.page = page;
    }
    /*
    public int countAcrossPages(Locator itemLocator, Locator nextArrow) {
        int total = 0;
        while (true) {
            int count = itemLocator.count();
            total += count;
            // If next arrow is missing or hidden → stop
            if (nextArrow.count() == 0 || !nextArrow.isVisible()) {
                break;
            }
            nextArrow.click();
            page.waitForLoadState(LoadState.NETWORKIDLE);
            itemLocator.first().waitFor();
        }
        System.out.println("The total amount is " + total);
        return total;
    }
*/




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





