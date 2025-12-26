package utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

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

            // Wait until at least one item is attached (not visible!)
            page.waitForCondition(() -> itemLocator.count() > 0);

            // Collect current page
            results.addAll(itemLocator.allInnerTexts());

            // Stop if next arrow button does not exist or is disabled
            if (nextArrow.count() == 0 || !nextArrow.isVisible()) {
                break;
            }

            nextArrow.click();

            // Now wait for new items to appear
           //itemLocator.first().waitFor();

            itemLocator.last().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        }

        return results;
    }
}





