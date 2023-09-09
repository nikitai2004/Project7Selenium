package pages;

import components.CourseCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    @FindBy(css = ".sc-gg1znw-0 > div")
    private List<WebElement> courses;
    private List<CourseCard> cards = new ArrayList<>();

    @FindBy(css = ".sc-pzx9cw-0")
    private WebElement menu;

    @FindBy(css = "a[href=\"/about\"] .sc-nrqod9-1")
    private WebElement aboutUs;

    @FindBy(css = ".sc-r03h0s-5 >span[title='Обучение']")
    private WebElement teaching;

    @FindBy(xpath = "//a[contains(text(),'Календарь мероприятий')]")
    private WebElement calendarEvents;

    @FindBy(css = ".dod_new-events-dropdown > div > span")
    private WebElement allEvents;

    @FindBy(css = ".dod_new-events-dropdown  >.dod_new-events-dropdown__list > a[href=\"/events/near/open_lesson/\"]")
    private WebElement openWebinar;

    WebDriverWait wait = new WebDriverWait(driver, 5);

    public MainPage(WebDriver driver) {
        super(driver);
        for (WebElement element : courses)
            cards.add(new CourseCard(element));
        url = "http://otus.ru";
    }

    public CatalogPage menuClick(int el) {
        String loc = "div:nth-child(" + el + ")";
        System.out.println("loc =" + loc);
        menu.findElement(By.cssSelector(loc)).click();
        wait.until(ExpectedConditions.urlContains("catalog/"));
        return new CatalogPage(driver);
    }

    public CalendarEvents menuClickCalendar() {
        teaching.click();
        calendarEvents.click();
        wait.until(ExpectedConditions.urlContains("events/near/"));
        return new CalendarEvents(driver);
    }

    public CalendarEventsOpenWebinar menuClickCalendarOpenWebinar() {
        teaching.click();
        calendarEvents.click();
        wait.until(ExpectedConditions.urlContains("events/near/"));
        allEvents.click();
        openWebinar.click();
        wait.until(ExpectedConditions.urlContains("near/open_lesson/"));
        return new CalendarEventsOpenWebinar(driver);
    }

    public AboutUsPage aboutUsClick() {
        aboutUs.click();
        wait.until(ExpectedConditions.urlContains("about"));
        return new AboutUsPage(driver);
    }

    public List<CourseCard> getCards() {
        return cards;
    }
}