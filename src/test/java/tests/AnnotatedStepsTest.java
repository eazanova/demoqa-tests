package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

/**
 * Title
 *
 * @author eazanova
 * @since 21.04.2022
 */
public class AnnotatedStepsTest
{
    private static final String REPOSITORY = "eazanova/demoqa-tests";
    private static final int ISSUE_NUMBER = 1;

    @Test
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();

        steps.mainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
