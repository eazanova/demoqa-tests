package tests;

import com.codeborne.selenide.Configuration;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistartionFormPage;

/**
 * @author eazanova
 * @since 03.04.2022
 */
public class RegistrationFormTest
{
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            userNumber = faker.phoneNumber().phoneNumber(),
            currentAddress = faker.rickAndMorty().quote(),
            gender = "Female",
            subject = "Biology",
            hobby = "Music",
            state = "NCR",
            city = "Delhi",
            path = "1.png",
            day = "13",
            month = "September",
            year = "2000";

    RegistartionFormPage registartionFormPage = new RegistartionFormPage();

    @BeforeAll
    static void setUp()
    {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void testRegistrationStudent()
    {
        registartionFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setDate(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .setImage(path)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .clickSubmit();

        registartionFormPage.checkResult(firstName)
                .checkResult(lastName)
                .checkResult(userEmail)
                .checkResult(gender)
                .checkResult(userNumber)
                .checkResult(day)
                .checkResult(month)
                .checkResult(year)
                .checkResult(subject)
                .checkResult(hobby)
                .checkResult(path)
                .checkResult(currentAddress)
                .checkResult(state)
                .checkResult(city);
    }
}
