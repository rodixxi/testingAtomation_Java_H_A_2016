
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/**
 * Created by rodixxi on 05/07/17.
 */
public class CommonSteps {
    private static WebDriver driver;
    private static String USER_ID = "usr";
    private static String USER_PWD = "pwd";
    private static By BUTTON_SUBMIT = By.xpath("//input[@value='Login']");
    private static String appURL = "http://testing-ground.scraping.pro/login";
    private static By WELCOME_MESSAGE = By.xpath("//div[@id='case_login']/h3");

    // Setting chromedriver driver
    public static void settingUPChrome() throws IOException {
        // Call chromedriver.
        String chromediverPath = new File("./chromedriver").getCanonicalPath();
        System.setProperty("webdriver.chrome.driver", chromediverPath);
        ChromeOptions options = new ChromeOptions();
        //Disable barInfo
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
    }

    public static void doLogin(String USER, String PASS){
        driver.navigate().to(appURL);
        WebElement userName_editbox = driver.findElement(By.id(USER_ID));
        WebElement password_editbox = driver.findElement(By.id(USER_PWD));
        WebElement submit_button = driver.findElement(BUTTON_SUBMIT);
        userName_editbox.sendKeys(USER);
        password_editbox.sendKeys(PASS);
        submit_button.click();
    }

    public static void isUserLoggedProperly(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(WELCOME_MESSAGE));
        String aux = driver.findElement(WELCOME_MESSAGE).getText();
        Assert.assertEquals(aux,"WELCOME :)");
    }

    public static void closeAllBrowsers(){
        driver.close();
    }


}
