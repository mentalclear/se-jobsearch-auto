package javascript;

import base.BaseTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JavaScriptTests extends BaseTests {
    @Test
    public void scrollDownTest() {
        var largeNDeepDOMPage = homePage.clickLargeAndDeepDom();
        largeNDeepDOMPage.scrollToTable();
    }

    @Test
    public void infiniteScrollTest() {
        var infiniteScrollPage = homePage.clickInfiniteScroll();
        infiniteScrollPage.scrollToParagraph(5);
    }

    @Test
    public void selectTwoOptionsInDropdown() {
        var dropdownPage = homePage.clickDropDown();
        dropdownPage.selectMultipleItems();

        var optionsToSelect = List.of("Option 1", "Option 2");
        optionsToSelect.forEach(dropdownPage::selectFromDropDown);

        var selectedOptions = dropdownPage.getSelectedOptions();
        assertTrue(selectedOptions.containsAll(optionsToSelect), "All options were not selected");
        assertEquals(selectedOptions.size(), optionsToSelect.size(), "Number of selected items");
    }
}
