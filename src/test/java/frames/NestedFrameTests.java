package frames;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NestedFrameTests extends BaseTests {
    @Test
    public void nestedFramesLeftAndBottomTest() {
        var framesPage = homePage.clickFrames();
        var nestedFramesPage = framesPage.clickNestedFrame();
        assertEquals(nestedFramesPage.selectFrameLeft().trim(), "LEFT");
        assertEquals(nestedFramesPage.selectFrameBottom().trim(), "BOTTOM");
    }
}
