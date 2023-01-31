package hover;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HoverTests extends BaseTests {

    @Test
    public void testHoversUser1() {
        var hoversPage = homePage.clickHovers();

        var caption = hoversPage.hoverOverFigure(1);
        assertTrue(caption.isCaptionDisplayed(), "Caption isn't displayed");
        assertEquals(caption.getTitle(), "name: user1", "Caption title is incorrect");
        assertEquals(caption.getLinkText(), "View profile", "Caption link is incorrect");
        assertTrue(caption.getLink().endsWith("/users/1"), "The link is incorrect");
    }
}
