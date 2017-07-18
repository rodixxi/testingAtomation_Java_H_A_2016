package tests;


import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;


/**
 * Created by rodixxi on 11/07/17.
 */
public class EjerciciosPosta {

    public static WebDriver driver;

    public String urlPosta = "file:///home/rodixxi/Clase%20automation%20ma%F1ana/LocalNew/Yahoo.html";
    public String urlOtra = "file:///home/rodixxi/Clase%20automation%20ma%F1ana/LocalNew/turno/index";
    public String loginUser = "saraza";
    public String pass = "1234";
    public By bottonIngresar = By.cssSelector("a[href='Yahoo_ingreso.html']");
    public By userField = By.cssSelector("#login-username");
    public By loggingBottom = By.cssSelector("#login-signin");
    public By passField = By.cssSelector("#login-passwd");
    public By passBottom = By.cssSelector("#login-signina");
    public By jubilacionButtom = By.cssSelector("a[href='jubilacion.html']");
    public By jubilacacionSelect = By.cssSelector("select[name='ctl00$ContentPlaceHolder1$ddlPrestaciones']");
    public By claptcha = By.cssSelector("img[src='jub2_files/CaptchaImage.jpeg']");
    public By cuil = By.cssSelector("input[name='ctl00$ContentPlaceHolder1$txtClaveTit']");

    public void openURL(String url, String titleExpexted) {
        driver.get(url);
        Assert.assertEquals(titleExpexted, driver.getTitle());
    }

    public void openURL(String url) {
        driver.get(url);
    }

    public String getLogAndGetTitle(String password){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.numberOfElementsToBe((bottonIngresar),1));
        WebElement mail_bottom = driver.findElement(bottonIngresar);
        mail_bottom.click();
        wait.until(ExpectedConditions.numberOfElementsToBe((userField),1));
        WebElement user_input = driver.findElement(userField);
        user_input.sendKeys(loginUser);
        WebElement logging_bottom = driver.findElement(loggingBottom);
        logging_bottom.click();
        wait.until(ExpectedConditions.numberOfElementsToBe((passField),1));
        WebElement pass_input = driver.findElement(passField);
        pass_input.sendKeys(password);
        WebElement pass_bottom = driver.findElement(passBottom);
        pass_bottom.click();
        String aux = driver.getTitle();
        return aux;
    }

    @BeforeClass
    public static void testSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }

    @Test(priority=1)
    public void enterYahoo(){
        openURL(urlPosta, "Yahoo");
    }


    @Test(priority=2)
    public void login(){
        String aux = "";
        aux = getLogAndGetTitle(pass);
        aux = aux.substring(aux.length() -12, aux.length());
        Assert.assertEquals("Yahoo Correo", aux);
    }

    @Test(priority=3)
    public void badLogin(){
        openURL(urlPosta, "Yahoo");
        String aux = "";
        aux = getLogAndGetTitle("123");
        Assert.assertNotEquals("Yahoo Correo", aux);
    }

    @Test(priority=4)
    public void sacarTurno(){
        openURL(urlOtra);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.numberOfElementsToBe((jubilacionButtom),1));
        WebElement jubilacion_buttom = driver.findElement(jubilacionButtom);
        jubilacion_buttom.click();
        wait.until(ExpectedConditions.numberOfElementsToBe((jubilacacionSelect),1));
        new Select(driver.findElement(jubilacacionSelect)).selectByVisibleText("Jubilación");
        wait.until(ExpectedConditions.numberOfElementsToBe((claptcha),1));
    }

    @Test(priority=5)
    public void onlyNumeric(){
        WebElement cuil_input = driver.findElement(cuil);
        cuil_input.sendKeys("1rQ3-[ 5*dvÑ ?? 43 D");
        String aux =cuil_input.getAttribute("value");
        Assert.assertTrue(aux.matches("^[0-9]*$"));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}