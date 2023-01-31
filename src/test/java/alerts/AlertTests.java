package alerts;

import base.BaseTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AlertsPage;
import pages.HomePage;

import static org.testng.Assert.assertEquals;

public class AlertTests extends BaseTests {

    private AlertsPage alertsPage;
    @BeforeMethod
    public void navToAlertsPage() {
        alertsPage = homePage.clickJavascriptAlerts();
    }

    @Test
    public void testAcceptAlert() {
        alertsPage.triggerAlert();
        alertsPage.acceptAlert();
        assertEquals(alertsPage.getResult(),
                "You successfully clicked an alert",
                "The result text incorrect/missing");
    }

    @Test
    public void testConfirmAlert() {
        alertsPage.triggerConfirm();
        String text = alertsPage.getConfirmText();
        assertEquals(text,
                "I am a JS Confirm",
                "Confirmation alert text incorrect/missing");
        alertsPage.acceptAlert();;
        assertEquals(alertsPage.getResult(),
                "You clicked: Ok",
                "The result text incorrect/missing");
    }

    @Test
    public void testDismissConfirmAlert() {
        alertsPage.triggerConfirm();
        alertsPage.dismissAlert();
        assertEquals(alertsPage.getResult(),
                "You clicked: Cancel",
                "The result text incorrect/missing");
    }

    @Test
    public void testSetInputInAlert() {
        alertsPage.setTriggerPrompt();
        String text = "I'm the best!";
        alertsPage.setAlertInputText(text);
        alertsPage.acceptAlert();
        assertEquals(alertsPage.getResult(),
                "You entered: " + text,
                "The result text incorrect/missing");
    }

    @Test
    public void testSetInputInAlertCancel() {
        alertsPage.setTriggerPrompt();
        String text = "I'm the best!";
        alertsPage.setAlertInputText(text);
        alertsPage.dismissAlert();
        assertEquals(alertsPage.getResult(),
                "You entered: null",
                "The result text incorrect/missing");
    }
}
