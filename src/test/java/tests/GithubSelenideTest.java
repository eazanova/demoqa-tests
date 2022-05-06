package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Title
 *
 * @author eazanova
 * @since 06.05.2022
 */
public class GithubSelenideTest
{
    @BeforeAll
    static void setUp ()
    {
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    public void testFindExampleJunit5()
    {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-body").find(byText("Soft assertions")).click();
        $$("#wiki-body").find(Condition.text("Using JUnit5 extend test class")).shouldBe(Condition.visible);
    }
}