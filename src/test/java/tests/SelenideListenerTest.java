package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.withText;

/**
 * Title
 *
 * @author eazanova
 * @since 21.04.2022
 */
public class SelenideListenerTest
{
    String repository = "eazanova/demoqa-tests";

    @Test
    @DisplayName("Тестирование Issue на github")
    public void testGithubIssue () {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Selenide.open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").setValue(repository);
        $(".header-search-input").submit();
        $(By.linkText(repository)).click();;
        $(By.partialLinkText("Issue")).click();
        $(withText("#2")).should(Condition.visible);
    }
}
