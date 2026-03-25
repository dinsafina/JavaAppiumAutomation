package tests;

import base.BaseTest;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WikiTest extends BaseTest {

    @Test
    @Description("Поиск по слову Java и выбор страницы в wiki")
    public void selectJavaPageTest() {
        getMainPage().clickSearch();
        getMainPage().sendKeys("Java");
        getSearchPage().oopJavaResultClick();
        getJavaPage().closeBtnIfPresent();
        Assertions.assertEquals("Java (programming language)", getArticlePage().getTitleText());
    }

    @Test
    @Description("Простая прокрутка страницы вниз")
    public void scrollJavaArticleTest() {
        getMainPage().clickSearch();
        getMainPage().sendKeys("Java");
        getSearchPage().oopJavaResultClick();
        getJavaPage().closeBtnIfPresent();
        getJavaPage().swipeUpJavaPage();
    }

    @Test
    @Description("Прокрутка страницы до нужного элемента")
    public void scrollToElementTest() {
        getMainPage().clickSearch();
        getMainPage().sendKeys("Appium");
        getSearchPage().appiumResultClick();
        getJavaPage().closeBtnIfPresent();
        getAppiumPage().scrollToFooterElement();
    }

    @Test
    @Description("Клик по кнопке Назад в поле ввода поиска по тексту")
    public void clickBtnBackInputSearch() {
        getMainPage().clickSearch();
        getMainPage().clickBackBtn();
    }

    @Test
    @Description("Проверка очистки поля ввода")
    public void clearTextInputTest() {
        getMainPage().clickSearch();
        getMainPage().sendKeys("Java");
        getMainPage().clearInput();
    }

    @Test
    @Description("Проверка наличия слова 'Java' в результатах поиска")
    public void checkSearchResultJava() {
        getMainPage().clickSearch();
        getMainPage().sendKeys("Java");
        getSearchPage().checkTextJava();
    }

    @Test
    @Description("Добавление в Сохраненное статьи Java и ее последующее удаление")
    public void addToReadingList() {
        getMainPage().clickSearch();
        getMainPage().sendKeys("Java");
        getSearchPage().oopJavaResultClick();
        getJavaPage().closeBtnIfPresent();
        getJavaPage().saveBtnClick();
        getJavaPage().addToListClick();
        getJavaPage().enterTextInField("Test");
        getJavaPage().OKBtnClick();
        getJavaPage().viewListClick();
        getJavaPage().gotItClickIfPresent();
        Assertions.assertEquals("Java (programming language)", getArticlePage().getTitleText(), "Невалидный заголовок");
        getArticlePage().swipeLeftArticleJava();
        getArticlePage().elementDisappear();
    }
}
