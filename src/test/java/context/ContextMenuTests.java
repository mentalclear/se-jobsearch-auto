package context;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ContextMenuTests extends BaseTests {

    @Test
    public void contextMenuRightClickTextTest() {
        var contextPage = homePage.clickContextMenu();
        contextPage.issueRightClick();
        assertEquals(contextPage.getAlertText(), "You selected a context menu");
        contextPage.acceptAlert();
    }
}
