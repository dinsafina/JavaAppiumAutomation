package pages;

import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    private final By searchInput = By.xpath("//*[@text='Search Wikipedia']");
    private final By backBtnMainPage = By.xpath("//*[@content-desc='Navigate up']");
    private final By searchInputResultPage = By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']");

    private final By skipBtn =  By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']");
    private final By closeBtn =  By.xpath("//*[@resource-id='org.wikipedia:id/closeButton']");


    public MainPage(AndroidDriver driver) {
        super(driver);
    }

    @Description("Клик по кнопке 'Skip' после запуска приложения")
    public void clickSkipBtnFirstPage() {
        clickElement(skipBtn);
    }

    @Description("Закрыть баннер при его наличии")
    public void closeBannerIfPresent() {
        clickIfVisibleAndClickable(closeBtn, 5);
    }

    @Description("Закрывает модальное окно при наличии")
    public void closeBtnClickIfPresent() {
        clickElement(closeBtn);
    }

    @Description("Ожидание кликабельности и клик")
    public void clickSearch() {
        clickElement(searchInput);
    }

    @Description("Ввод значения в поле поиска")
    public void sendKeys(String text) {
        sendValue(searchInput, text);
    }

    @Description("Клик по кнопке Назад в поле поиска")
    public void clickBackBtn() {
        clickElement(backBtnMainPage);
    }

    @Description("Очищает поле поиска")
    public void clearInput() {
        WebElement el = driver.findElement(searchInputResultPage);
        el.clear();
    }
}
