package tests;

import java.io.File;

import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class Firefox extends Common{
	private RemoteWebDriver driver;
	@BeforeClass
	public void testSetUp() {
		File file = new File("firebug-3.0.0-alpha.1.xpi");
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.addExtension(file);
		firefoxProfile.setPreference("extensions.firebug.currentVersion", "3.0.0-alpha.1"); // Avoid startup screen
		firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
		try {
		    driver = new FirefoxDriver(firefoxProfile);
		} catch(Exception e) {
		    System.setProperty("webdriver.gecko.driver","geckodriver");
		}
		
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
