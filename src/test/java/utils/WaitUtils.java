package utils;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private static final long DEFAULT_TIMEOUT = 10;

    private static AndroidDriver getDriver() {
        return BaseTest.getDriver();
    }

    /*Высокоуровневый метод, ждет кликабельности и кликает*/
    public static WebElement waitForElementAndClick(By locator) {
        WebElement el = waitForClickable(locator, "Элемент не стал кликабельным");
        el.click();
        return el;
    }

    public static void waitForElementAndSendKeys(By locator, String text) {
        WebElement el = waitForClickable(locator, "Поле не стало доступным для ввода");
        el.clear();
        el.sendKeys(text);
//         проверка, что текст реально ввёлся
//         wait.until(d -> el.getAttribute("value").contains(text));

        // Проверяем, что значение реально появилось в поле
        WebDriverWait waitShort = new WebDriverWait(getDriver(), Duration.ofSeconds(4));

        try {
            waitShort.until(d -> {
                String actual = el.getAttribute("text");
                return actual != null && actual.contains(text);   // или .equals(text)
            });
        } catch (TimeoutException e) {
            throw new AssertionError(
                    String.format("Текст '%s' не появился в поле после sendKeys (locator: %s). Текущее значение: '%s'",
                            text, locator, el.getAttribute("value")), e);
        }
    }

    @Description("Ожидание исчезновения элемента на странице")
    public static void waitForElementNotPresent(By locator, long timeoutSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds))
                .withMessage("Элемент всё ещё присутствует/видим → " + locator)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private static WebElement waitForClickable(By locator, String message) {
        try {
            return new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException(
                    message + " → " + locator + " (timeout: " + DEFAULT_TIMEOUT + "s)",
                    e.getCause()
            );
        }
    }

    public static WebElement waitForVisible(By locator, long timeoutSeconds, String message) {
        try {
            return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException(
                    message + " → " + locator + " (timeout: " + timeoutSeconds + "s)",
                    e.getCause()
            );
        }
    }

    private static WebElement waitForVisible(By locator, String message) {
        return waitForVisible(locator, DEFAULT_TIMEOUT, message);
    }
}
