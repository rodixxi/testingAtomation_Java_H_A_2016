/**
 * Created by rodixxi on 06/07/17.
 */
import org.junit.*;

import java.io.IOException;

public class ValidForm {

    @BeforeClass
    public static void TestSettingUPChrome() {
        try {
            CommonSteps.settingUPChrome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void goToPage(){
        CommonSteps.goToPage();
        CommonSteps.clickOnLinkTest();
        CommonSteps.readTable();
        CommonSteps.goBack();
        CommonSteps.setNames("Rodrigo","Crespillo");
        CommonSteps.setSex("F");
        CommonSteps.setExp("6");
        CommonSteps.setDate("07/07/1992");
        CommonSteps.setProfession("Automation Tester");
        try {
            CommonSteps.uploadFIle("./lala");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @AfterClass
    public static void killSession(){
        CommonSteps.closeAllBrowsers();
    }
}
