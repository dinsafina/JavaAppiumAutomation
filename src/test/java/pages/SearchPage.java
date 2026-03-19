package pages;

import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;

public class SearchPage extends BasePage {

    private final By programmingLanguage = By.xpath("//*[@text='Object-oriented programming language']");

    public SearchPage(AndroidDriver driver) {
        super(driver);
    }

    @Description("Ввод значения в поле поиска")
    public void oopJavaResultClick() {
        clickElement(programmingLanguage);
    }
}
