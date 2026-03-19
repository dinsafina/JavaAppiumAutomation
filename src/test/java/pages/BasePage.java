package pages;

import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import utils.WaitUtils;

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
}
