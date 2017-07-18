package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rodixxi on 13/07/17.
 */
public class Test1 {

    public static WebDriver driver;

    public String url1 = "file://" + new File("final/index.html").getCanonicalPath() ;
    public String url2 = "file://" + new File("final/travels.html").getCanonicalPath() ;

    public String userInput = "input:not(#resetemail)[name='email']";
    public String passInput = "input[name='password']";
    public String loginButton = "button[type='submit']";

    public String userLogin = " admin@phptravels.com";
    public String userPass = "demoadmin";

    public String firstCar = "a[href='#Cars']";
    public String secondCar = "a[href='admin.html']";
    public String addNewCar = "button[type='submit']";
    public String status = "select[name='carstatus']";
    public String name = "input[name='carname']";
    public String starts = "select[name='carstars']";
    public String passangers = "select[name='passangers']";
    public String doors = "select[name='doors']";
    public String baggage = "select[name='baggage']";
    public String location = "input[name='location']";
    public String dropOffLoc = "input[name='dropOffLoc']";
    public String price = "input[name='locations[1][price]']";
    public String featured = "#isfeatured";
    public String submit = "#add";

    public String carTab = "a[href='#CARS']";
    public String fromField = "#s2id_carlocations > a";
    public String toField = "#s2id_carlocations2 > a";
    public String fromList = "#select2-drop > ul.select2-results > li > div";
    public String toList = "#select3-drop > ul.select2-results > li  > div";


    public Test1() throws IOException {
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
    public void logingOK(){
        waitForSomething(firstCar);
        String aux = driver.getTitle();
        Assert.assertEquals(aux, "Dashboard");
    }

    @Test(priority=4)
    public void createNewProfile(){
        WebElement firstCar_button = driver.findElement(By.cssSelector(firstCar));
        firstCar_button.click();
        waitForSomething(secondCar);
        WebElement secondCar_button = driver.findElement(By.cssSelector(secondCar));
        secondCar_button.click();
        String aux = driver.getTitle();
        waitForSomething(addNewCar);
        Assert.assertEquals(aux, "Cars Management");
        WebElement add_button = driver.findElement(By.cssSelector(addNewCar));
        add_button.click();
        waitForSomething(status);
        aux = driver.getTitle();
        Assert.assertEquals(aux, "Add Car");

        WebElement status_selector = driver.findElement(By.cssSelector(status));
        WebElement name_input = driver.findElement(By.cssSelector(name));
        WebElement starts_select = driver.findElement(By.cssSelector(starts));
        WebElement passangers_selector = driver.findElement(By.cssSelector(passangers));
        WebElement doors_selector = driver.findElement(By.cssSelector(doors));
        WebElement baggage_selector = driver.findElement(By.cssSelector(baggage));
        WebElement location_input = driver.findElement(By.cssSelector(location));
        WebElement dropOffLoc_input = driver.findElement(By.cssSelector(dropOffLoc));
        WebElement price_input = driver.findElement(By.cssSelector(price));
        WebElement featured_select = driver.findElement(By.cssSelector(featured));
        WebElement submit_button = driver.findElement(By.cssSelector(submit));

        new Select(status_selector).selectByVisibleText("Enabled");
        name_input.sendKeys("name");
        new Select(starts_select).selectByVisibleText("5");
        new Select(passangers_selector).selectByVisibleText("5");
        new Select(doors_selector).selectByVisibleText("4");
        new Select(baggage_selector).selectByVisibleText("x6");
        location_input.sendKeys("Buenos Aires");
        dropOffLoc_input.sendKeys("Cordoba");
        price_input.sendKeys("2000");
        new Select(featured_select).selectByVisibleText("Yes");
        submit_button.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority=5)
    public void goToSeconURL(){
        openURL(url2, "PHPTRAVELS | Travel Technology Partner");
    }

    @Test(priority=6)
    public void goToCarsTab(){
        WebElement cars_link = driver.findElement(By.cssSelector(carTab));
        cars_link.click();
    }

    @Test(priority=7)
    public void selectDestination(){
        waitForSomething(fromField);
        WebElement from_link = driver.findElement(By.cssSelector(fromField));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        from_link.click();
        selecFromList(fromList, "Buenos Aires");
        WebElement to_link = driver.findElement(By.cssSelector(toField));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        to_link.click();
        selecFromList(toList, "Cordoba");
        System.out.println("lala");
    }

    public void selecFromList(String list, String option){
        ArrayList<WebElement> from_input = (ArrayList<WebElement>) driver.findElements(By.cssSelector(list));
        for (WebElement div_option : from_input){
            String aux = div_option.getText();
            if (aux.equals(option)){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                div_option.click();
            }
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
