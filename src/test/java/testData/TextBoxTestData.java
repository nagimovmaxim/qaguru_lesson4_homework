package testData;

import testUtils.Creator;

import java.util.LinkedHashMap;

public class TextBoxTestData {
    private String userEmailIncorrect;
    private LinkedHashMap<String, String> correctFormData = new LinkedHashMap<>();
    private String formUrl;

    public TextBoxTestData() throws Exception {
        formUrl = "/text-box";
        userEmailIncorrect = "qwert";
        correctFormData.put("name", Creator.getRandomLetterString(10));
        correctFormData.put("email", Creator.getRandomLetterString(10) + "@" + Creator.getRandomLetterString(5) + ".com");
        correctFormData.put("currentAddress", Creator.getRandomLetterString(50));
        correctFormData.put("permanentAddress", Creator.getRandomLetterString(50));
    }

    public String getUserEmailIncorrect() {
        return userEmailIncorrect;
    }

    public String getName() {
        return correctFormData.get("name");
    }

    public String getEmail() {
        return correctFormData.get("email");
    }

    public String getCurrentAddress() {
        return correctFormData.get("currentAddress");
    }

    public String getPermanentAddress() {
        return correctFormData.get("permanentAddress");
    }

    public LinkedHashMap<String, String> getCorrectFormData() {
        return correctFormData;
    }

    public String getFormUrl() {
        return formUrl;
    }
}