package forgot;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ForgotEmailTest extends BaseTests {
    @Test
    public void forgottenEmailRetrievalTest() {
        String testEmailAddress = "exampel@example.com";
        var forgottenPasswordPage = homePage.clickForgotPassword();

        forgottenPasswordPage.fillTheEmail(testEmailAddress);
        var confirmationPage = forgottenPasswordPage.clickRetrievePassword();
        assertEquals(confirmationPage.getReplyText(),
                "Internal Server Error",
                "Expected text cannot be found on the page");
    }
}
