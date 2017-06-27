/**
 * Created by rodixxi on 26/06/17.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleLandingPage {
    private WebDriver driver;
    String appURL = "http://google.com";

    @BeforeClass
// Setting FirefoxDriver driver
    public void testSetUp() {

        System.setProperty("webdriver.gecko.driver", "/home/rodixxi/geckoDriver/geckodriver");

        driver = new FirefoxDriver(); }
    @Test
// Ejecuting Test
    public void verifyGooglePageTittle() {
        driver.navigate().to(appURL);
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, "Google");
    }
    @AfterClass
// Closing Browser when finish the test
    public void tearDown() { driver.quit(); }
}
