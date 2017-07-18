package tests;

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
import java.util.Arrays;

/**
 * Created by rodixxi on 13/07/17.
 */
public class Test2 {
    public static WebDriver driver;

    public String url1 = "file://" + new File("final/index.html").getCanonicalPath() ;
    public String url2 = "file://" + new File("final/travels.html").getCanonicalPath() ;

    public String userInput = "input:not(#resetemail)[name='email']";
    public String passInput = "input[name='password']";
    public String loginButton = "button[type='submit']";

    public String userLogin = " admin@phptravels.com";
    public String userPass = "1234";
    public String alert = "div.resultlogin > div.alert-danger";

    public Test2() throws IOException {
    }

    public void openURL(String url, String titleExpexted) {
        driver.get(url);
        Assert.assertEquals(driver.getTitle(), titleExpexted);
    }

    public void openURL(String url) {
        driver.get(url);
    }

    private void waitForSomething(String something) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.numberOfElementsToBe((By.cssSelector(something)),1));
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
    public void enterURL(){
        openURL(url1, "Administator Login");
    }

    @Test(priority=2)
    public void logInTo(){
        waitForSomething(loginButton);
        WebElement user_input = driver.findElement(By.cssSelector(userInput));
        WebElement pass_input = driver.findElement(By.cssSelector(passInput));
        WebElement login_button = driver.findElement(By.cssSelector(loginButton));
        user_input.sendKeys(userLogin);
        pass_input.sendKeys(userPass);
        login_button.click();
    }

    @Test(priority=3)
    public void logingFAIL(){
        waitForSomething(alert);
        WebElement alert_div = driver.findElement(By.cssSelector(alert));
        String aux = alert_div.getText();
        Assert.assertEquals(aux, "Wrong User or Password");
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
