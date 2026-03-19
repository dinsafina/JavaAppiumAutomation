package pages;

import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;

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
    public void clickIfVisibleAndClickable(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));

            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();

        } catch (TimeoutException ignored) {
        }
    }
}
