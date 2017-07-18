package tests;

import java.util.Arrays;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class Chrome extends Common{
	private RemoteWebDriver driver;
	@BeforeClass
	public void testSetUp() {
	   System.setProperty("webdriver.chrome.driver", "chromedriver");
	   ChromeOptions options = new ChromeOptions();
	   options.addArguments("disable-infobars");
	   DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	   capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
	   capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	   driver = new ChromeDriver(capabilities);
	}
	
	@Test
	public void openPageOnChrome() throws InterruptedException {
		driver.get(baseUrl);
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void tearDown() {
	   driver.quit();
	}

}
