import org.junit.*;

import java.io.IOException;

/**
 * Created by rodixxi on 05/07/17.
 */
public class ValidUser {

    @BeforeClass
    public static void TestSettingUPChrome() throws IOException {
        CommonSteps.settingUPChrome();
    }

    public void login(){
        String USER = "admin";
        String PASS = "12345";
        CommonSteps.doLogin(USER,PASS);
    }

    @Test
    public void isUserLogged(){
        login();
        CommonSteps.isUserLoggedProperly();
    }

    @After
    public void testSaveScreenshott () {
        System.out.println("I've been invoked after each method");
    }

    @AfterClass
    public static void testCloseBrowser() {
        CommonSteps.closeAllBrowsers();
    }


}
