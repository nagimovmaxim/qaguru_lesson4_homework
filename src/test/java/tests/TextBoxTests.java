package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testData.TextBoxTestData.*;

public class TextBoxTests extends TestBase {

    @Test
    @Description("Негативная проверка на неправильнео заполнение почты")
    void negativeMailErrorTextBoxTest() {
        open(formUrl);
        $(byId("userEmail")).setValue(userEmailIncorrect);
        $(byId("submit")).click();
        $(byId("userEmail")).shouldHave(cssValue("border-color", "rgb(255, 0, 0)"));
    }

    @Test
    @Description("ПОзитивная проверка заполнения всех полей")
    void successfulFullSubmitTextBoxTest() {
        open(formUrl);
        $(byId("userName")).setValue(name);
        $(byId("userEmail")).setValue(email);
        $(byId("currentAddress")).setValue(currentAddress);
        $(byId("permanentAddress")).setValue(permanentAddress);
        $(byId("submit")).click();
        $(byId("output")).$(byId("name")).shouldHave(text(name));
        $(byId("output")).$(byId("email")).shouldHave(text(email));
        $(byId("output")).$(byId("currentAddress")).shouldHave(text(currentAddress));
        $(byId("output")).$(byId("permanentAddress")).shouldHave(text(permanentAddress));
    }
}