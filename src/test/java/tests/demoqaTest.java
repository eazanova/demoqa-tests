package tests;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author eazanova
 * @since 03.04.2022
 */
public class demoqaTest
{
    @BeforeAll
    static void setUp()
    {
        //Configuration.browser = Browsers.FIREFOX;
        //Configuration.holdBrowserOpen = true;
        //WebDriverManager.chromedriver().clearDriverCache().forceDownload().setup();
        Configuration.baseUrl = "https://demoqa.com";
        //Configuration
        Configuration.browserSize = "1920x1080";    }

    @Test
    void testfirst()
    {
        open("/automation-practice-form");
        $("#firstName").setValue("name");
        $("#lastName").setValue("name2");
        $("#userEmail").setValue("name@n.ru");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1986");
        $("[aria-label$='September 13th, 1986']").click();
        $("#subjectsInput").setValue("History").pressEnter();

        $(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath("file/1.png");
        $("#currentAddress").setValue("Street");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR"));
        $("#city").$(byText("Delhi"));

        //$x("//button[@id='submit']").click();


        $("[id=example-modal-sizes-title-lg]").shouldHave(Condition.text("Thanks for submitting the form"));

        //$("#submit").click();

    }
}
