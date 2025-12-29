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

    public List<String> collectJobDataAcrossPages(Locator itemLocator, Locator itemId, Locator nextArrow) {
        List<String> results = new ArrayList<>();

        while (true) {

            // Wait until at least one item is visible!)
            itemLocator.last().waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE));

            // Collect current page
            results.addAll(itemLocator.allInnerTexts());

            // Stop if the next arrow button does not exist
            if (nextArrow.count() == 0 || !nextArrow.isVisible()) {
               //System.out.println("Next button missing or invisible");
                break;
            } else if (nextArrow.isVisible() && nextArrow.isEnabled()) {


                String lastJobId = itemId.last().getAttribute("data-ph-at-job-id-text");
                //System.out.println("this is the jobID: " + lastJobId);

                nextArrow.click();
                itemLocator.last().scrollIntoViewIfNeeded(); // making sure new content is in view

            // Wait until last job ID changes (new page loaded)
            page.waitForCondition(() -> {
                String newLastJobId = itemId.last().getAttribute("data-ph-at-job-id-text");
                return newLastJobId != null && !newLastJobId.equals(lastJobId);
            });
        }}

        return results;
    }
}





