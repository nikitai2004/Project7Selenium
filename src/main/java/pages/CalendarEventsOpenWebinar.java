package pages;

import components.NewCourseDateOpenWebinar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CalendarEventsOpenWebinar extends BasePage {
    private final Logger log = LogManager.getLogger(CalendarEventsOpenWebinar.class);

    private final ArrayList<String> names = new ArrayList<>();
    @FindBy(css = ".dod_new-type__text")
    private List<WebElement> nameEventOpenWebinar;

    public CalendarEventsOpenWebinar(WebDriver driver) {
        super(driver);
        for (WebElement element : nameEventOpenWebinar) {
            log.info(" Тип мероприятия 2 = " + new NewCourseDateOpenWebinar(element).getNameOpenWebinar());
            names.add(new NewCourseDateOpenWebinar(element).getNameOpenWebinar());
        }
    }

    public ArrayList<String> getNamesOpenWebinar() {
        return names;
    }
}