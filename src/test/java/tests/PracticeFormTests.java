package tests;

import com.codeborne.selenide.SelenideElement;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testData.PracticeFormTestData.*;

public class PracticeFormTests extends TestBase {

    @Test
    @Description("Негативная проверка на неправильное заполенние телефона")
    void negativePhoneErrorPracticeFormTest() {
        open(formUrl);
        $(byId("firstName")).setValue(firstName);
        $(byId("lastName")).setValue(lastName);
        $(byId("genterWrapper")).$(byValue(gender)).click();
        $(byId("userNumber")).setValue(userNumberIncorrect);
        $(byId("submit")).click();
        $(byId("userNumber")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    @Description("Негативная проверка на неправильное заполнение почты")
    void negativeMailErrorPracticeFormTest() {
        open(formUrl);
        $(byId("firstName")).setValue(firstName);
        $(byId("lastName")).setValue(lastName);
        $(byId("genterWrapper")).$(byValue(gender)).click();
        $(byId("userNumber")).setValue(userNumber);
        $(byId("userEmail")).setValue(userEmailIncorrect);
        $(byId("submit")).click();
        $(byId("userEmail")).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    @Description("Негативная проверка на незаполнение всех обязательных полей")
    void negativeShortSubmitPracticeFormTest() {
        open(formUrl);
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
        open(formUrl);
        $(byId("firstName")).setValue(firstName);
        $(byId("lastName")).setValue(lastName);
        $(byId("genterWrapper")).$(byValue(gender)).click();
        $(byId("userNumber")).setValue(userNumber);
        $(byId("submit")).click();
        $(byClassName("table-responsive")).should(appear);
        $(byClassName("table-responsive")).$("tbody").$$("tr").forEach(x -> {
            String key = x.$$("td").get(0).text();
            SelenideElement value = x.$$("td").get(1);
            if (key.equals("Student Name"))
                value.shouldHave(text(firstName + " " + lastName));
            if (key.equals("Gender"))
                value.shouldHave(text(gender));
            if (key.equals("Mobile"))
                value.shouldHave(text(userNumber));
        });
    }


    @Test
    @Description("Позитивно проверяются все поля формы, а не только обязательные")
    void positiveFullSubmitPracticeFormTest() {
        open(formUrl);
        $(byId("firstName")).setValue(firstName);

        $(byId("lastName")).setValue(lastName);

        $(byId("userEmail")).setValue(userEmail);

        $(byId("genterWrapper")).$(byValue(gender)).click();

        $(byId("userNumber")).setValue(userNumber);

        $(byId("dateOfBirthInput")).click();
        $(byClassName("react-datepicker__year-select")).selectOption(birthYear);
        $(byClassName("react-datepicker__month-select")).selectOption(birthMonth);
        $(byClassName("react-datepicker__month")).$(byText(birthDay)).click();

        $(byId("subjectsInput")).sendKeys(subjects);
        $(byAttribute("value", subjects)).should(appear).sendKeys(Keys.ENTER);

        $(byId("hobbiesWrapper")).$(byText(hobbies)).click();

        $(byId("uploadPicture")).uploadFromClasspath(picture);

        $("textarea[id=currentAddress]").setValue(address);

        $(byId("react-select-3-input")).sendKeys(state);
        $(byId("react-select-3-input")).shouldHave(attribute("value", state))
                .sendKeys(Keys.ENTER);

        $(byId("react-select-4-input")).sendKeys(city);
        $(byId("react-select-4-input")).shouldHave(attribute("value", city))
                .sendKeys(Keys.ENTER);

        $(byId("submit")).click();

        $(byClassName("table-responsive")).should(appear);

        $(byClassName("table-responsive")).$("tbody").$$("tr").forEach(x -> {
            String key = x.$$("td").get(0).text();
            SelenideElement value = x.$$("td").get(1);
            if (key.equals("Student Name"))
                value.shouldHave(text(firstName + " " + lastName));
            if (key.equals("Student Email"))
                value.shouldHave(text(userEmail));
            if (key.equals("Gender"))
                value.shouldHave(text(gender));
            if (key.equals("Mobile"))
                value.shouldHave(text(userNumber));
            if (key.equals("Date of Birth"))
                value.shouldHave(text(birthDay + " " + birthMonth + "," + birthYear));
            if (key.equals("Subjects"))
                value.shouldHave(text(subjects));
            if (key.equals("Hobbies"))
                value.shouldHave(text(hobbies));
            if (key.equals("Picture"))
                value.shouldHave(text(picture));
            if (key.equals("Address"))
                value.shouldHave(text(address));
            if (key.equals("State and City"))
                value.shouldHave(text(state + " " + city));
        });
    }
}