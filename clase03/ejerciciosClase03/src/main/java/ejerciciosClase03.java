import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ejerciciosClase03 {

    private WebDriver driver;
    String appURL = new File("../../clase03/localWebs/busqueda/index.html").getCanonicalPath();


    public ejerciciosClase03() throws IOException {
    }


    @BeforeClass //Setting chromedriver driver
    public void testSetIp(){
        //call chromedriver
        String chromediverPath = null;
        try {
            chromediverPath = new File("../../chromedriver.exe").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", chromediverPath);
        //Disable barInfo
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);

    }


    public void search(String search){
        //Open URL
        driver.get(appURL);
        //Verify URL
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, "");
        //Fill out search input
        driver.findElement(By.xpath("//*[@id='searchbox']")).sendKeys(search);
        //Click search
        driver.findElement(By.xpath("//*[@class='mic']")).click();
    }

    public void waitForAndClick(String x_element){
        //Wait next page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(x_element)));
        //click the Gatos node
        driver.findElement(By.xpath(x_element)).click();
    }


    @Test //Ejecuting test
    public void ejercicio01(){
        search("gatos");
        waitForAndClick("//a[text()='Gatos']");
        waitForAndClick("//a[2]");
        Assert.assertEquals(driver.getTitle(), "derp.jpg (1200×640)");
    }


    @Test
    public void ejercicio02(){
        search("cosas");
        waitForAndClick("//a[text()='Wikipedia cosas']");
        Assert.assertEquals(driver.getTitle(), "cosas Wikia");
    }


    @Test
    public void ejercicio03() {
        search("la comida");
        waitForAndClick("//a[text()='Noticias']");
        waitForAndClick("//a[last()]");
    }

    @Test
    public void ejercicio04(){
        search("algo");
        waitForAndClick("//a[text()='Peliculas! XD']");
        Assert.assertEquals(driver.getTitle(), "IMDb - Movies, TV and Celebrities - IMDb");
    }


    @AfterClass
    // Closing Browser when finish the test
    public void tearDown() {
        driver.quit();
    }


}