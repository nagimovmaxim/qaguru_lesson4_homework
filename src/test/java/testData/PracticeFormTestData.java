package testData;

import testUtils.Creator;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class PracticeFormTestData {
    private LinkedHashMap<String, String> correctFormData = new LinkedHashMap<>();
    private final Set<Integer> numFieldsForShortSubmit = new HashSet<>();
    private String userEmailIncorrect;
    private String userNumberIncorrect;
    private String formUrl;


    public PracticeFormTestData() throws Exception {
        formUrl = "/automation-practice-form";
        userEmailIncorrect = "qwert";
        userNumberIncorrect = "qwert";

        numFieldsForShortSubmit.addAll(Set.of(1, 3, 4));

        correctFormData.put("Student Name", Creator.getRandomLetterString(10) + " " + Creator.getRandomLetterString(10));
        correctFormData.put("Student Email", Creator.getRandomLetterString(10) + "@" + Creator.getRandomLetterString(5) + ".com");
        correctFormData.put("Gender", "Other");
        correctFormData.put("Mobile", Creator.getRandomNumericString(10));
        correctFormData.put("Date of Birth", "31 December,1999");
        correctFormData.put("Subjects", "Maths");
        correctFormData.put("Hobbies", "Reading");
        correctFormData.put("Picture", "images.jpg");
        correctFormData.put("Address", Creator.getRandomLetterString(50));
        correctFormData.put("State and City", "NCR Delhi");
    }

    public String getFirstName() {
        return correctFormData.get("Student Name").split(" ")[0];
    }

    public String getLastName() {
        return correctFormData.get("Student Name").split(" ")[1];
    }

    public String getUserEmail() {
        return correctFormData.get("Student Email");
    }

    public String getGender() {
        return correctFormData.get("Gender");
    }

    public String getUserNumber() {
        return correctFormData.get("Mobile");
    }

    public String getYear() {
        return correctFormData.get("Date of Birth").split(",")[1];
    }

    public String getMonth() {
        return correctFormData.get("Date of Birth").split(",")[0].split(" ")[1];
    }

    public String getDay() {
        return correctFormData.get("Date of Birth").split(",")[0].split(" ")[0];
    }

    public String getSubjects() {
        return correctFormData.get("Subjects");
    }

    public String getHobbies() {
        return correctFormData.get("Hobbies");
    }

    public String getPicture() {
        return correctFormData.get("Picture");
    }

    public String getCurrentAddress() {
        return correctFormData.get("Address");
    }

    public String getState() {
        return correctFormData.get("State and City").split(" ")[0];
    }

    public String getCity() {
        return correctFormData.get("State and City").split(" ")[1];
    }

    public LinkedHashMap<String, String> getCorrectFormData() {
        return correctFormData;
    }

    public Set<Integer> getNumFieldsForShortSubmit() {
        return numFieldsForShortSubmit;
    }

    public String getUserEmailIncorrect() {
        return userEmailIncorrect;
    }

    public String getUserNumberIncorrect() {
        return userNumberIncorrect;
    }

    public String getFormUrl() {
        return formUrl;
    }
}