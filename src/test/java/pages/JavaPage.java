package pages;

import io.appium.java_client.android.AndroidDriver;
import jdk.jfr.Description;
import org.openqa.selenium.By;

public class JavaPage extends BasePage{

    private final By javaTitle = By.xpath("//*[@text='Java (programming language)']");
    private final By saveBtn = By.xpath("//*[@resource-id='org.wikipedia:id/page_save']");
    private final By addToListBtn = By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']");
    private final By textInput = By.xpath("//*[@resource-id='org.wikipedia:id/text_input']");
    private final By okBtn = By.xpath("//*[@resource-id='android:id/button1']");
    private final By viewList = By.xpath("//*[@text='View list']");

    public JavaPage(AndroidDriver driver) {
        super(driver);
    }

    @Description("Прокрутка вверх")
    public void swipeUpJavaPage(){
        swipeUp();
    }

    @Description("Клик по кнопке 'Save' в нижней панели")
    public void saveBtnClick(){
        clickElement(saveBtn);
    }

    @Description("Клик по кнопке 'Add to list' в сплывающем окне")
    public void addToListClick(){
        clickElement(addToListBtn);
    }

    @Description("Ввод названия списка закладок")
    public void enterTextInField(String text){
        sendValue(textInput, text);
    }

    @Description("Клик по кнопке ОК")
    public void OKBtnClick() {
        clickElement(okBtn);
    }

    @Description("Клик по кнопке 'View List' в сплывающем окне")
    public void viewListClick() {
        clickElement(viewList);
    }
}
