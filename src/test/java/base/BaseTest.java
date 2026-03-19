package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.JavaPage;
import pages.MainPage;
import pages.SearchPage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected static AndroidDriver driver;

    private MainPage mainPage;
    private SearchPage searchPage;
    private JavaPage javaPage;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setUdid("emulator-5554")
                .setPlatformVersion("16.0")
                .setAppPackage("org.wikipedia")
                .setAppActivity("org.wikipedia.main.MainActivity")
                .setNoReset(false);


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @BeforeEach
    public void skipClickBtn(){
       getMainPage().clickSkipBtnFirstPage();
       getMainPage().closeBannerIfPresent();
    }

    @AfterEach
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

    protected MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage(driver);
        }
        return mainPage;
    }

    protected SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage(driver);
        }
        return searchPage;
    }

    protected JavaPage getJavaPage() {
        if(javaPage == null) {
            javaPage = new JavaPage(driver);
        }
        return javaPage;
    }
}
