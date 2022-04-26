package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Title
 *
 * @author eazanova
 * @since 26.04.2022
 */
public class SelenideFilesTest
{
    @Test
    void downloadTest() throws IOException
    {
        Selenide.open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadFile = Selenide.$("#raw-url").download();
        try (InputStream is = new FileInputStream(downloadFile))
        {
            Assertions.assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8)
                    .contains("This repository is the home of the next generation of JUnit"));
        }
    }

    @Test
    void uploadFileTest()
    {
        Selenide.open("https://the-internet.herokuapp.com/upload");
        Selenide.$("input[type='file']").uploadFromClasspath("1.png");
        Selenide.$("#file-submit").click();
        Selenide.$("div.example").shouldHave(Condition.text("File Uploaded!"));
        Selenide.$("#uploaded-files").shouldHave(Condition.text("1.png"));
    }
}
