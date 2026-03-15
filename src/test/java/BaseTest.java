import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected static AndroidDriver driver;

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
                .setNoReset(true);


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @AfterAll
    public static void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void firstTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = driver.findElement(By.xpath("//*[@text='Search Wikipedia']"));
        element.click();

        WebElement searchField = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Search Wikipedia']"))
        );
        searchField.sendKeys("Portugal");
        System.out.println("First test run");
    }


    public int test(int year) {
        int century = year - 1 / 100;
        return century;
    }
    @Test
    public void run(){
        System.out.println(test(2010));
    }

}
