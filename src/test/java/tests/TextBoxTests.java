package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testData.TextBoxTestData;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase {
    TextBoxTestData testData;

    @BeforeEach
    @Description("Параметризация тестовых данных")
    void parametrizationTestData() throws Exception {
        testData = new TextBoxTestData();
    }

    @Test
    @Description("Негативная проверка на неправильнео заполнение почты")
    void negativeMailErrorTextBoxTest() {
        open(testData.getFormUrl());
        $(byId("userEmail")).setValue(testData.getUserEmailIncorrect());
        $(byId("submit")).click();
        $(byId("userEmail")).shouldHave(cssValue("border-color", "rgb(255, 0, 0)"));
    }

    @Test
    @Description("ПОзитивная проверка заполнения всех полей")
    void successfulFullSubmitTextBoxTest() {
        open(testData.getFormUrl());
        $(byId("userName")).setValue(testData.getName());
        $(byId("userEmail")).setValue(testData.getEmail());
        $(byId("currentAddress")).setValue(testData.getCurrentAddress());
        $(byId("permanentAddress")).setValue(testData.getPermanentAddress());
        $(byId("submit")).click();

        testData.getCorrectFormData().keySet().forEach(x -> {
            $(byId("output")).$(byId(x)).shouldHave(text(testData.getCorrectFormData().get(x)));
        });
    }
}