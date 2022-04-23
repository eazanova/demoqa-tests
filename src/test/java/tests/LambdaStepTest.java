package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;


/**
 * Title
 *
 * @author eazanova
 * @since 21.04.2022
 */
public class LambdaStepTest
{
    @Test
    @DisplayName("Тестирование Issue на github")
    public void testGithubIssue () {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Allure.step("Открываем сайт", () ->{
            Selenide.open("https://github.com");
        });

        Allure.step("Поиск", () ->{
            $(".header-search-input").click();
            $(".header-search-input").setValue("eazanova/demoqa-tests");
            $(".header-search-input").submit();
        });

        Allure.step("Переходим в проект", () ->{
            $(By.linkText("eazanova/demoqa-tests")).click();;
        });

        Allure.step("Переходим на вкладку", () ->{
            $(By.partialLinkText("Issue")).click();
        });

        Allure.step("Проверяем", () ->{
            $(withText("#2")).should(Condition.visible);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
