import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rodixxi on 06/07/17.
 */
public class CommonSteps {
    private static WebDriver driver;
    private static String appURL = "http://toolsqa.com/automation-practice-form/";
    private static String userFirstName = "input[name='firstname']";
    private static String userLastName = "input[name='lastname']";
    private static String linkTest = "a[title='Automation Practice Table']";
    private static String tableElements = "table[summary='Sample Table'] > tbody > tr";
    private static String chinaHight = "table[summary='Sample Table'] > tbody > tr:nth-child(4) > td:nth-child(4)";
    private static String taiwanRank = "table[summary='Sample Table'] > tbody > tr:nth-child(3) > td:nth-child(6)";
    private static String womanSelector = "#sex-1";
    private static String manSelector = "#sex-0";
    private static String datePicker = "#datepicker";
    private static String idUploadElem = "photo";

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

    public static void goToPage(){
        driver.navigate().to(appURL);
    }

    public static void clickOnLinkTest(){
        WebElement linkTest_link = driver.findElement(By.cssSelector(linkTest));
        linkTest_link.click();
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Demo Table for practicing Selenium Automation");
    }

    public static void readTable(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table[summary='Sample Table']"),1));
        List<WebElement> tableElements_table = driver.findElements(By.cssSelector(tableElements));
        int aux = tableElements_table.size();
        WebElement chinaHight_cell = driver.findElement(By.cssSelector(chinaHight));
        WebElement taiwanTank_cell = driver.findElement(By.cssSelector(taiwanRank));
        Assert.assertEquals(aux, 4);
        Assert.assertEquals(chinaHight_cell.getText(), "492m");
        Assert.assertNotEquals(taiwanTank_cell.getText(), "2");
    }

    public static void goBack(){
        driver.navigate().back();
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Demo Form for practicing Selenium Automation");
    }

    public static void setNames(String firstName, String lastName){
        WebElement userFirstName_editbox = driver.findElement(By.cssSelector(userFirstName));
        WebElement userLastName_editbox = driver.findElement(By.cssSelector(userLastName));
        userFirstName_editbox.sendKeys(firstName);
        userLastName_editbox.sendKeys(lastName);
    }

    public static void setSex(String sex){
        WebElement userSexM_selector = driver.findElement(By.cssSelector(manSelector));
        WebElement userSexF_selector = driver.findElement(By.cssSelector(womanSelector));
        if (sex == "M"){
            userSexM_selector.click();
        }
        else if (sex == "F"){
            userSexF_selector.click();
        }
    }

    public static void setExp(String exp){
        WebElement userExp_selector = driver.findElement(By.cssSelector("input[name='exp'][value='" + exp + "']"));
        userExp_selector.click();
    }

    public static void setDate(String date){
        WebElement datePicker_datePicker = driver.findElement(By.cssSelector(datePicker));
        datePicker_datePicker.sendKeys(date);
    }

    public static void setProfession(String profession){
        WebElement profession_selector = driver.findElement(By.cssSelector("input[name='profession'][value='" + profession + "']"));
        profession_selector.click();
    }

    public static void uploadFIle(String file) throws IOException {
        WebElement myUploadElem = driver.findElement(By.id(idUploadElem));
        String absolutePath = new File(file).getCanonicalPath();
        myUploadElem.sendKeys(absolutePath);
    }


    public static void closeAllBrowsers(){
        driver.close();
    }

}
