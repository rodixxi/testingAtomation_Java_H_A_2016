/**
 * Created by rodixxi on 27/06/17.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleDiverTest {

    private WebDriver driver;
    String appURL = "https://www.google.com.ar";
    protected String GOOGLE_TITLE= "Google";
    protected String GOOGLE_INPUT_ID = "lst-ib";
    protected String GOOGLE_SEARCH_BUTTTON = "_fZl";
    protected By WIKIPEDIA_LINK = By.xpath("//div[@id='rso']/div/div/div/div/div/h3/a");
    protected String WIKIPEDIA_TITLE ="Cats - Wikipedia, la enciclopedia libre";
    protected String CATS_TITLE ="CATS: Crash Arena Turbo Stars - Aplicaciones de Android en Google Play";
    protected String WORDS_TO_SEARCH_IN_GOOGLE= "CATS";

    @BeforeClass // Setting chromedriver driver
    public void testSetUp() {
        // Call chromedriver.
        System.setProperty("webdriver.chrome.driver", "/home/rodixxi/Repositories/testingAutomation_Java_H_A_2017/chromedriver");
                //Disable barInfo
                ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(options);
    }
    /*
    @Test
    // Ejecuting Test
    public void verifyWikepediaPageTittle() {
        //Open Google
        driver.get(appURL);
        //Verify Google Title
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, GOOGLE_TITLE);
        //Fill out search input
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys(WORDS_TO_SEARCH_IN_GOOGLE);
        //Press search button
        driver.findElement(By.id(GOOGLE_SEARCH_BUTTTON)).click();
        //Wait till GOOGLE_IMAGES_LINK is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(WIKIPEDIA_LINK));
        //Press search button
        driver.findElement(WIKIPEDIA_LINK).click();
        //Verify Wikipedia Title
        getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, WIKIPEDIA_TITLE);
    }
    */
    @Test
    // Ejecuting Test
    public void verifyCatsPageTittle() {
        //Open Google
        driver.get(appURL);
        //Verify Google Title
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, GOOGLE_TITLE);
        //Fill out search input
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys(WORDS_TO_SEARCH_IN_GOOGLE);
        //Press search button
        driver.findElement(By.id(GOOGLE_SEARCH_BUTTTON)).click();
        //Wait till GOOGLE_IMAGES_LINK is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(WIKIPEDIA_LINK));
        //Press search button
        driver.findElement(WIKIPEDIA_LINK).click();
        //Verify Wikipedia Title
        getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, CATS_TITLE);
    }

    @Test
    public void scenario1(){
        driver.get(appURL);
        //Verify Google Title
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, GOOGLE_TITLE);
        //Fill out search input
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys("https://accounts.google.com");
        //Press search button
        driver.findElement(By.id(GOOGLE_SEARCH_BUTTTON)).click();
        //Wait till GOOGLE_IMAGES_LINK is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(WIKIPEDIA_LINK));
        //Press search button
        driver.findElement(WIKIPEDIA_LINK).click();
        getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, "My Account");
    }

    @AfterClass
    // Closing Browser when finish the test
    public void tearDown() {
        driver.quit();
    }



}
