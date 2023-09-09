package pages;

import components.NewCourseDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMMM yyyy")
                    .withLocale(Locale.forLanguageTag("ru-RU"));
            LocalDate date2L = LocalDate.parse(dat2, df);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant instant = date2L.plusDays(1).atStartOfDay(defaultZoneId).toInstant();
            Date date2 = Date.from(instant);
            dates.add(date2);
            Logger log = LogManager.getLogger(CalendarEvents.class);
            log.info(" Дата мероприятия = " + date2);
        }
    }

    public ArrayList<Date> getDates() {
        return dates;
    }
}