package pages;

import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    private final By programmingLanguage = By.xpath("//*[@text='Object-oriented programming language']");

    private final By titleResultsJava = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']");

    private final By appium = By.xpath("//*[@text='Automation for Apps']");

    public SearchPage(AndroidDriver driver) {
        super(driver);
    }

    @Description("Клик по результату поиска Java")
    public void oopJavaResultClick() {
        clickElement(programmingLanguage);
    }

    @Description("Клик по результату поиска Appium")
    public void appiumResultClick() {
        clickElement(appium);
    }

    @Description("Проверка текста 'Java' в локаторах")
    public void checkTextJava(){
        List<WebElement> results = driver.findElements(titleResultsJava);
        for (WebElement result : results) {
            result.getText();
        }
    }
}
