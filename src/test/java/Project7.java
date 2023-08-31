import components.prop;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CoursePage;
import pages.MainPage;
import settings.PropertiesReader;

import java.util.*;

import static org.apache.commons.lang3.math.NumberUtils.toInt;
import static org.junit.Assert.assertEquals;

public class Project7 {
    private final Logger log = LogManager.getLogger(Project7.class);
    private final Map<String, String> settings = new PropertiesReader().read();
    private WebDriver driver;

    @BeforeAll
    public static void install() {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void setDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void test_1_QuantityTestCards() {
        driver.get(settings.get("url"));
        int link = new MainPage(driver).menuClick(toInt(settings.get("number_testing"))).getCards().stream().toList().size();
        Assertions.assertEquals(String.valueOf(link), settings.get("test_cards"));
        log.info("Количество карточек = " + link);
    }

    @Test
    public void test_2_InfoCourseTestCard() {
        driver.get(settings.get("url"));
        CoursePage page = new MainPage(driver).menuClick(toInt(settings.get("number_testing"))).getCards().get(toInt(settings.get("number_card_of_testing"))).click(driver);
        log.info(" Длительность = " + page.getDuration());
        log.info(" Дата начала = " + page.getStartFrom());
        log.info(" Название = " + page.getTitle());
        log.info(" Описание = " + page.getDescriptionTest());
        log.info(" Формат = " + page.formatTest());

        Assertions.assertEquals(page.getDuration(), prop.duration);
        Assertions.assertEquals(page.getStartFrom(), prop.startFrom);
        Assertions.assertEquals(page.getTitle(), prop.title);
        Assertions.assertEquals(page.getDescriptionTest(), prop.description);
        Assertions.assertEquals(page.formatTest(), prop.format);
    }

    @Test
    public void test_3_CheckCalendarEvents() {
        driver.get(settings.get("url"));
        List<Date> dates = new MainPage(driver).menuClickCalendar().getDates().stream().toList();
        for (Date date : dates) {
            Date date1 = new Date();
            Assertions.assertTrue(date1.before(date) || date1.equals(date));
        }
    }

    @Test
    public void test_4_CheckOpenWebinars() {
        driver.get(settings.get("url"));
        List<String> names = new MainPage(driver).menuClickCalendarOpenWebinar().getNamesOpenWebinar().stream().toList();

        for (String name : names) {
            String name1 = "Открытый вебинар";
            Assertions.assertEquals(name, name1);
        }
    }
}