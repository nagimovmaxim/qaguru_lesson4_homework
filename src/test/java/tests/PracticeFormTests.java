package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import testData.PracticeFormTestData;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests extends TestBase {
    PracticeFormTestData testData;

    @BeforeEach
    @Description("Параметризация тестовых данных")
    void parametrizationTestData() throws Exception {
        testData = new PracticeFormTestData();
    }

    @Test
    @Description("Негативная проверка на неправильное заполенние телефона")
    void negativePhoneErrorPracticeFormTest() {
        open(testData.getFormUrl());
        $(byId("firstName")).setValue(testData.getFirstName());
        $(byId("lastName")).setValue(testData.getLastName());
        $(byId("genterWrapper")).$(byValue(testData.getGender())).click();
        $(byId("userNumber")).setValue(testData.getUserNumberIncorrect());
        $(byId("submit")).click();
        $(byId("userNumber")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    @Description("Негативная проверка на неправильное заполнение почты")
    void negativeMailErrorPracticeFormTest() {
        open(testData.getFormUrl());
        $(byId("firstName")).setValue(testData.getFirstName());
        $(byId("lastName")).setValue(testData.getLastName());
        $(byId("genterWrapper")).$(byValue(testData.getGender())).click();
        $(byId("userNumber")).setValue(testData.getUserNumber());
        $(byId("userEmail")).setValue(testData.getUserEmailIncorrect());
        $(byId("submit")).click();
        $(byId("userEmail")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    @Description("Негативная проверка на незаполнение всех обязательных полей")
    void negativeShortSubmitPracticeFormTest() {
        open(testData.getFormUrl());
        $(byId("submit")).click();
        $(byId("firstName")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(byId("lastName")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(byId("gender-radio-1")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(by("for", "gender-radio-1")).shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $(byId("gender-radio-2")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(by("for", "gender-radio-2")).shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $(byId("gender-radio-3")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $(by("for", "gender-radio-3")).shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $(byId("userNumber")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    @Description("Позитивно проверяются только обязательные поля формы")
    void positiveShortSubmitPracticeFormTest() {
        open(testData.getFormUrl());
        $(byId("firstName")).setValue(testData.getFirstName());
        $(byId("lastName")).setValue(testData.getLastName());
        $(byId("genterWrapper")).$(byValue(testData.getGender())).click();
        $(byId("userNumber")).setValue(testData.getUserNumber());
        $(byId("submit")).click();
        $(byClassName("table-responsive")).should(appear);
        $(byClassName("table-responsive")).$("tbody").$$("tr").forEach(x -> {
            String key = x.$$("td").get(0).text();
            Integer numField = (new ArrayList<>(testData.getCorrectFormData().keySet())).indexOf(key) + 1;
            if (testData.getNumFieldsForShortSubmit().contains(numField)) {
                x.$$("td").get(1).shouldHave(text(testData.getCorrectFormData().get(key)));
            }
        });
    }


    @Test
    @Description("Позитивно проверяются все поля формы, а не только обязательные")
    void positiveFullSubmitPracticeFormTest() {

        open(testData.getFormUrl());
        $(byId("firstName")).setValue(testData.getFirstName());

        $(byId("lastName")).setValue(testData.getLastName());

        $(byId("userEmail")).setValue(testData.getUserEmail());

        $(byId("genterWrapper")).$(byValue(testData.getGender())).click();

        $(byId("userNumber")).setValue(testData.getUserNumber());

        $(byId("dateOfBirthInput")).click();
        $(byClassName("react-datepicker__year-select")).selectOption(testData.getYear());
        $(byClassName("react-datepicker__month-select")).selectOption(testData.getMonth());
        $(byClassName("react-datepicker__month")).$(byText(testData.getDay())).click();

        $(byId("subjectsInput")).sendKeys(testData.getSubjects());
        $(byAttribute("value", testData.getSubjects())).should(appear).sendKeys(Keys.ENTER);

        $(byId("hobbiesWrapper")).$(byText(testData.getHobbies())).click();

        $(byId("uploadPicture")).uploadFromClasspath(testData.getPicture());

        $("textarea[id=currentAddress]").setValue(testData.getCurrentAddress());

        $(byId("react-select-3-input")).sendKeys(testData.getState());
        $(byId("react-select-3-input")).shouldHave(attribute("value", testData.getState()))
                .sendKeys(Keys.ENTER);

        $(byId("react-select-4-input")).sendKeys(testData.getCity());
        $(byId("react-select-4-input")).shouldHave(attribute("value", testData.getCity()))
                .sendKeys(Keys.ENTER);

        $(byId("submit")).click();

        $(byClassName("table-responsive")).should(appear);

        $(byClassName("table-responsive")).$("tbody").$$("tr").forEach(x -> {
            x.$$("td").get(1).shouldHave(text(testData.getCorrectFormData().get(x.$$("td").get(0).text())));
        });
    }
}