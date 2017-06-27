import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleHomePageTest {

    private WebDriver driver;
    String appURL = "http://google.com";


    @BeforeClass
    //Setting FirefoxDriver driver
    public void testSetUp(){
        //if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
        System.setProperty("webdriver.gecko.driver", "C:/Program Files/geckodriver/geckodriver.exe");

        driver = new FirefoxDriver();}
    @Test
    //Ejecuting Test
    public void verifyGooglePageTittle(){
        driver.navigate().to(appURL);
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, "Google");
    }
    @AfterClass
    //Closing Browser when finished the test
    public void tearDown(){driver.quit();}
}
