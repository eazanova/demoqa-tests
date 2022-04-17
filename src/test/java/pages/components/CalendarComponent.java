package pages.components;

import static com.codeborne.selenide.Selenide.$;

/**
 * Title
 *
 * @author eazanova
 * @since 17.04.2022
 */
public class CalendarComponent
{
    public void setDate(String day, String month, String year)
    {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}
