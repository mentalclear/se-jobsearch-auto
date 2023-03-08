package base;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePageOnGlassDoor;
import utils.EventReporter;
import utils.OSSelector;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTestsGlassDoor {
    // private WebDriver driver; // Without listener
    private EventFiringWebDriver driver;
    protected HomePageOnGlassDoor homePageOnGlassDoor;

    @BeforeClass
    public void setUp() {
        selectWebDriverForOS();
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        // driver = new ChromeDriver(); // without listener
        driver.register(new EventReporter());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); // waits for page load on project level
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // waits on a project level
        goHome();
        homePageOnGlassDoor = new HomePageOnGlassDoor(driver);
    }

    public static void selectWebDriverForOS() {
        var os = new OSSelector();
        String selectedDriver = "chromedriver";
        if(os.isLinux()) selectedDriver = "linux/chromedriver";
        if(os.isWindows()) selectedDriver = "windows/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", "resources/driver/" + selectedDriver);
    }
    @BeforeMethod
    public void goHome() {
        driver.get("https://www.glassdoor.com/index.htm");
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    @AfterMethod
    public void recordFailure(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
        }
    }
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        // For window size use: "--window-size=1920,1080"
        options.addArguments("start-maximized", "user-agent="+userAgent, "disable-infobars");
        // options.setHeadless(true); // Running in headless mode
        return options;
    }
}
