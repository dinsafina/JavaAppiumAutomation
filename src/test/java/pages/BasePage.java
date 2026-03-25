package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;
import java.util.Collections;

import static base.BaseTest.getDriver;
import static utils.WaitUtils.waitForVisible;

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
        final By closeBtn = By.xpath("//*[@resource-id='org.wikipedia:id/closeButton']");
        clickIfVisibleAndClickable(closeBtn, 5);
    }

    @Description("Кликает на кнопку 'Got it' при наличии")
    public void gotItClickIfPresent() {
        final By gotIt = By.xpath("//*[@text='Got it']");
        clickIfVisibleAndClickable(gotIt, 5);
    }

    @Description("Получение текста локатора с ожиданием")
    protected String getText(By locator, int timeoutSeconds, String message) {
        WebElement element = waitForVisible(locator, timeoutSeconds, message);
        return element.getText();
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

        swipe.addAction(new Pause(finger, Duration.ofMillis(180)));

        //свайп вниз/вверх/вправо/влево
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(750),
                PointerInput.Origin.viewport(),
                endX,
                endY
        ));

        //палец оторвался от экрана
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(new Pause(finger, Duration.ofMillis(150)));

        //Appium проигрывает все действия как одно движение
        driver.perform(Collections.singletonList(swipe));
    }

    @Description("Скролл до определенного элемента через текст")
    protected WebElement scrollToElementByText(String text) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        ));
    }

    @Description("Скролл через локатор")
    protected void swipeUntilElementVisible(By locator, int maxSwipes, boolean swipeUp) {
        int count = 0;
        while (driver.findElements(locator).isEmpty() && count < maxSwipes) {
            if (swipeUp) swipeUp();
            else swipeDown();
            count++;
        }
    }

    @Description("Свайп влево")
    protected void swipeLeft() {
        Dimension size = driver.manage().window().getSize();

        int y = size.height / 2;

        int startX = (int) (size.width * 0.8); // справа
        int endX = (int) (size.width * 0.2);   // слева

        if (startX <= endX) {
            throw new IllegalArgumentException("SwipeLeft: startX must be greater than endX");
        }
        performSwipe(startX, y, endX, y);
    }

    @Description("Свайп влево по элементу")
    protected void swipeLeftOnElement(By locator) {
        WebElement element = driver.findElement(locator);

        Point location = element.getLocation();
        Dimension size = element.getSize();

        int startX = location.getX() + (int) (size.getWidth() * 0.82);
        int endX = location.getX() + (int) (size.getWidth() * 0.12);

        int y = (int) (location.getY() + size.getHeight() * 0.5);

        // Если элемент слишком узкий — делаем свайп почти от края до края
        if (size.getWidth() < 450) {
            startX = location.getX() + (int) (size.getWidth() * 0.9);   // почти правый край
            endX = location.getX() + (int) (size.getWidth() * 0.08);  // почти левый край
        }

        //System.out.println("Swipe from (" + startX + ", " + y + ") to (" + endX + ", " + y + ")");

        performSwipe(startX, y, endX, y);
    }

    @Description("Свайп влево по элементу")
    protected void swipeLeftOnElement2(By locator) {
        WebElement element = driver.findElement(locator);

        Point location = element.getLocation();
        Dimension size = element.getSize();

        int startX = location.getX() + (int) (size.getWidth() * 0.7);
        int endX = location.getX() + (int) (size.getWidth() * 0.1);

        int y = (int) (location.getY() + size.getHeight() * 0.3);

        performSwipe(startX, y, endX, y);
    }

    @Description("Свайп вправо")
    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();

        int y = size.height / 2;

        int startX = (int) (size.width * 0.2); // слева
        int endX = (int) (size.width * 0.8);   // справа

        if (startX >= endX) {
            throw new IllegalArgumentException("SwipeRight: startX must be less than endX");
        }
        performSwipe(startX, y, endX, y);
    }

    @Description("Свайп вправо по элементу")
    protected void swipeRightOnElement(By locator) {
        WebElement element = driver.findElement(locator);

        Point location = element.getLocation();
        Dimension size = element.getSize();

        int startX = location.getX() + (int) (size.getWidth() * 0.1);
        int endX = location.getX() + (int) (size.getWidth() * 0.9);

        int y = location.getY() + size.getHeight() / 2;

        performSwipe(startX, y, endX, y);
    }
}
