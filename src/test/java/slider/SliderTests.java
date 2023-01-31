package slider;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SliderTests extends BaseTests {
    @Test
    public void testSliderMovedToFour() throws InterruptedException {
        var sliderPage = homePage.clickHorizontalSlider();
        sliderPage.changeRange(3.5);
        assertEquals(sliderPage.verifyTheValue(), 3.5);
    }
}
