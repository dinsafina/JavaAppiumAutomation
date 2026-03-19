package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;
import java.util.Collections;

import static base.BaseTest.getDriver;

public class BasePage {
    protected AndroidDriver driver;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
    }

    @Description("Ожидание кликабельности и клик")
    protected void clickElement(By locator) {
        WaitUtils.waitForElementAndClick(locator);
    }

    @Description("Ожидание кликабельности и ввод значения")
    protected void sendValue(By locator, String text) {
        WaitUtils.waitForElementAndSendKeys(locator, text);
    }

    protected void clickElem(By locator) {
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    @Description("Клик по элементу если он есть")
    private void clickIfVisibleAndClickable(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));

            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();

        } catch (TimeoutException ignored) {
        }
    }

    @Description("Закрывает модальное окно при наличии")
    public void closeBtnIfPresent() {
        final By closeBtn =  By.xpath("//*[@resource-id='org.wikipedia:id/closeButton']");
        clickIfVisibleAndClickable(closeBtn, 5);
    }


    /* ============================================================
                      МЕТОДЫ СКРОЛЛА СТРАНИЦЫ
     * ============================================================ */
    @Description("Прокрутка вверх")
    protected void swipeUp() {
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;

        int startY = (int) (size.height * 0.8); // низ
        int endY = (int) (size.height * 0.2);   // верх

        if (startY <= endY) {
            throw new IllegalArgumentException("SwipeUp: startY must be greater than endY");
        }

        performSwipe(x, startY, x, endY);
    }

    @Description("Прокрутка вниз")
    protected void swipeDown() {
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;

        int startY = (int) (size.height * 0.2); // верх
        int endY = (int) (size.height * 0.8);   // низ

        if (startY >= endY) {
            throw new IllegalArgumentException("SwipeDown: startY must be less than endY");
        }

        performSwipe(x, startY, x, endY);
    }

    @Description("Общий метод для прокрутки (переиспользуемый)")
    private void performSwipe(int startX, int startY, int endX, int endY) {
        //Это будет касание пальцем
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        //Создаём последовательность действий
        Sequence swipe = new Sequence(finger, 1);

        //палец телепортируется в точку (540, 400) Duration.ZERO = мгновенно
        swipe.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                startX,
                startY
        ));

        //палец коснулся экрана
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        //свайп вниз
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(500),
                PointerInput.Origin.viewport(),
                endX,
                endY
        ));

        //палец оторвался от экрана
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Appium проигрывает все действия как одно движение
        driver.perform(Collections.singletonList(swipe));
    }

    @Description("Скролл до определенного элемента через текст")
    public WebElement scrollToElementByText(String text) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        ));
    }

    @Description("Скролл через локатор")
    public void swipeUntilElementVisible(By locator, int maxSwipes, boolean swipeUp) {
        int count = 0;
        while (driver.findElements(locator).isEmpty() && count < maxSwipes) {
            if (swipeUp) swipeUp();
            else swipeDown();
            count++;
        }
    }
}
