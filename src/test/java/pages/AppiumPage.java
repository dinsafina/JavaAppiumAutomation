package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class AppiumPage extends BasePage{

    private final By footer = By.xpath("//*[@text='Content is available under ']");

    public AppiumPage(AndroidDriver driver) {
        super(driver);
    }

    public void scrollToFooterElement() {
        swipeUntilElementVisible(footer, 2, true);
    }
}
