package pages;

import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Title
 *
 * @author eazanova
 * @since 17.04.2022
 */
public class RegistartionFormPage
{
    CalendarComponent calendar = new CalendarComponent();

    public RegistartionFormPage openPage()
    {
        open("/automation-practice-form");
        return this;
    }

    public RegistartionFormPage setFirstName(String value)
    {
        $("#firstName").setValue(value);
        return this;
    }

    public RegistartionFormPage setLastName(String value)
    {
        $("#lastName").setValue(value);
        return this;
    }

    public RegistartionFormPage setUserEmail(String value)
    {
        $("#userEmail").setValue(value);
        return this;
    }

    public RegistartionFormPage setUserNumber(String value)
    {
        $("#userNumber").setValue(value);
        return this;
    }

    public RegistartionFormPage setGender(String value)
    {
        $("#genterWrapper").$(byText(value)).click();
        return this;
    }

    public RegistartionFormPage setDate(String day, String month, String year)
    {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistartionFormPage setSubject(String value) {
        $("#subjectsInput").setValue(value).pressEnter();
        return this;
    }

    public RegistartionFormPage setAddress(String value) {
        $("#currentAddress").setValue(value);
        return this;
    }

    public RegistartionFormPage setHobby(String value) {
        $(byText(value)).click();
        return this;
    }

    public RegistartionFormPage setState(String value) {
        $("#state").click();
        $(byText(value)).click();
        return this;
    }

    public RegistartionFormPage setCity(String value) {
        $("#city").click();
        $(byText(value)).click();
        return this;
    }

    public RegistartionFormPage setImage(String imgPath) {
        $("#uploadPicture").uploadFromClasspath(imgPath);
        return this;
    }

    public RegistartionFormPage clickSubmit() {
        $("#submit").click();
        return this;
    }

    public RegistartionFormPage checkResult(String value) {
        $(".modal-body").shouldHave(text(value));
        return this;
    }
}
