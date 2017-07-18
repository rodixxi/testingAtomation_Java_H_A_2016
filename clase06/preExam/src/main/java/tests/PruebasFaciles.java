package tests;

import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.Arrays;

/**
 * Created by rodixxi on 11/07/17.
 */
public class PruebasFaciles {
    public static WebDriver driver;

    public String firstURL = "http://www.msftncsi.com/ncsi.txt";
    public String secondURL = "http://motherfuckingwebsite.com/";
    public String thirdURL = "https://varvy.com/pagespeed/wicked-fast.html";

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

    @Test
    public void testFistURL(){
        driver.get(firstURL);
        Assert.assertEquals(firstURL, driver.getCurrentUrl());
    }

    @Test
    public void testSecondURL(){
        driver.get(secondURL);
        Assert.assertEquals(secondURL, driver.getCurrentUrl());
    }

    @Test
    public void testThirdURL(){
        driver.get(thirdURL);
        Assert.assertEquals(thirdURL, driver.getCurrentUrl());
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
