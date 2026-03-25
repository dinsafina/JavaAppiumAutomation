package pages;

import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;

import static utils.WaitUtils.waitForElementNotPresent;

public class ArticlePage extends BasePage{

    private final By javaTitle = By.xpath("//*[@text='Java (programming language)']");

    public ArticlePage(AndroidDriver driver) {
        super(driver);
    }

    @Description("Удаление заметки свайпом влево")
    public void swipeLeftArticleJava(){
        swipeLeftOnElement(javaTitle);
    }

    @Description("Получение текста локатора")
    public String getTitleText() {
        return getText(javaTitle, 5, "Не найден элемент с заголовком Java");
    }

    @Description("Ожидание исчезновения элемента на странице")
    public void elementDisappear(){
        waitForElementNotPresent(javaTitle, 10);
    }
}
