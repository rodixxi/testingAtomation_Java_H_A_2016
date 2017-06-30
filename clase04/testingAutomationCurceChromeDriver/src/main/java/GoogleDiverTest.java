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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    public void testSetUp() throws IOException {
        /* Call chromedriver. */
        String chromediverPath = new File("../../chromedriver.exe").getCanonicalPath();
        System.setProperty("webdriver.chrome.driver", chromediverPath);
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
    public void verifyWikepediaPageTittle()gi {
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
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(WIKIPEDIA_LINK));
        //Press search button
        driver.findElement(WIKIPEDIA_LINK).click();
        //Verify Wikipedia Title
        getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, "Cats");
    }

    @Test
    public void googleAccountLogin(){
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
        Assert.assertEquals(getTitle, "Mi Cuenta");
        driver.findElement(By.id("gb_70")).click();
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        WebElement element2 = wait2.until(ExpectedConditions.elementToBeClickable(By.id("identifierNext")));
        //Insertar Usuario
        driver.findElement(By.id("identifierId")).sendKeys("rodixxi");
        //Clickear en Next
        driver.findElement(By.id("identifierNext")).click();
        WebDriverWait wait3 = new WebDriverWait(driver, 15);
        WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
        //Insertar Contraseña
        driver.findElement(By.name("password")).sendKeys("MnbBLaq10q6V");
        //Clickear en Login
        driver.findElement(By.id("passwordNext")).click();
        //Verificar que el usuario se encuentra en la página correcta
        WebDriverWait wait4 = new WebDriverWait(driver, 20);
        WebElement element4 = wait4.until(ExpectedConditions.elementToBeClickable(By.linkText("https://www.google.com")));
        getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, "Mi Cuenta");
    }

    @AfterClass
    // Closing Browser when finish the test
    public void tearDown() {
        driver.quit();
    }



}
