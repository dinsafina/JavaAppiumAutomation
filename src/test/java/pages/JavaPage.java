package pages;

import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class JavaPage extends BasePage{

    private final By javaPageTitle = By.xpath("//android.webkit.WebView[@text='Java (programming language)']");

    public JavaPage(AndroidDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return WaitUtils.waitForElementAndClick(javaPageTitle).getAttribute("text");
    }

    @Description("Прокрутка вверх")
    public void swipeUpJavaPage(){
        swipeUp();
    }
}
