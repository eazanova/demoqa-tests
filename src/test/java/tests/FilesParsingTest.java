package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Title
 *
 * @author eazanova
 * @since 26.04.2022
 */
public class FilesParsingTest
{
    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void parsePdfTest() throws IOException
    {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownload = Selenide.$(Selectors.byText("PDF download")).download();
        PDF pdf = new PDF(pdfDownload);
        assertThat(pdf.author).contains("Marc");
    }

    @Test
    void parseXlsTest() throws IOException
    {
        Selenide.open("http://romashka2008.ru/price");
        File xlsDownload = Selenide.$(".site-main__inner a[href*='prajs']").download();
        XLS xls = new XLS(xlsDownload);
        assertThat(xls.excel.getSheetAt(0).getRow(11).getCell(1).getStringCellValue())
                .contains("Сахалинская");
    }

    @Test
    void parseCsvTest() throws Exception
    {
        try (InputStream is = classLoader.getResourceAsStream("access-code.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(is)))
        {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)).contains("Identifier;First name;Last name");
        }
    }

    @Test
    void parseZipTest() throws Exception
    {
        try (InputStream is = classLoader.getResourceAsStream("sdng.zip");
             ZipInputStream zis = new ZipInputStream(is))
        {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null)
            {
                assertThat(entry.getName()).isEqualTo("sdng.log");
            }
        }
    }

    @Test
    void parseZipAllTypeTest() throws Exception
    {
        try (InputStream is = classLoader.getResourceAsStream("allType.zip");
             ZipInputStream zis = new ZipInputStream(is))
        {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null)
            {
                if (entry.getName().equals("junit-user-guide-5.8.2.pdf"))
                {
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.author).contains("Marc");
                }
                else if (entry.getName().equals("prajs_ot_2104.xls"))
                {
                    XLS xls = new XLS(zis);
                    assertThat(xls.excel.getSheetAt(0).getRow(11).getCell(1).getStringCellValue())
                            .contains("Сахалинская");
                }
                else if (entry.getName().equals("access-code.csv"))
                {
                    CSVReader reader = new CSVReader(new InputStreamReader(is));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(0)).contains("Identifier;First name;Last name");
                }
            }
        }
    }
}
