package utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class PaginationHelper {

    private final Page page;

    public PaginationHelper(Page page) {
        this.page = page;
    }
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

}

