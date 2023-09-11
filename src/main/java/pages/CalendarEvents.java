package pages;

import components.NewCourseDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarEvents extends BasePage {
    private final ArrayList<Date> dates = new ArrayList<>();
    @FindBy(css = ".dod_new-event__time > span:nth-child(1)")
    private List<WebElement> dateEvent;

    public CalendarEvents(WebDriver driver) {
        super(driver);
        String dat2;
        NewCourseDate d1;
        for (WebElement element : dateEvent) {
            d1 = new NewCourseDate(element);
            if (d1.getCardDate().indexOf(' ') == 1) {
                dat2 = "0" + d1.getCardDate() + " " + LocalDate.now().getYear();
            } else {
                dat2 = d1.getCardDate() + " " + LocalDate.now().getYear();
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(Locale.forLanguageTag("ru-RU"));
            LocalDate localDate = LocalDate.parse(dat2, dateFormatter).plusDays(1);
            List<LocalDate> dates = new ArrayList<>();
            dates.add(localDate);
            Logger logger = LogManager.getLogger(CalendarEvents.class);
            logger.info("Дата мероприятия = " + localDate);
        }
    }

    public ArrayList<Date> getDates() {
        return dates;
    }
}