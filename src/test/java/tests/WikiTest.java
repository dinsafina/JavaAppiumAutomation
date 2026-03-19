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
        Assertions.assertEquals("Java (programming language)", getJavaPage().getTitleText(), "Невалидный заголовок");
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
}
