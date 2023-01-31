package loading;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DynamicLoadingTests extends BaseTests {
    @Test
    public void loadingIsDisplayedThenHelloWorldAppearsTest() {
        var dynamicLoadingExample1Page = homePage.clickDynamiclLoading().clickExample1();
        dynamicLoadingExample1Page.clickStartButton();
        assertEquals(dynamicLoadingExample1Page.getResultText(), "Hello World!", "Text is not there/wrong");
    }
}
