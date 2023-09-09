import components.Prop;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.CoursePage;
import pages.MainPage;
import settings.PropertiesReader;
import webDriverFactory.WebDriverFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Project7Test {
    private final Logger log = LogManager.getLogger(Project7Test.class);
    private final Map<String, String> settings = new PropertiesReader().read();
    private WebDriver driver;


    @BeforeAll
    public static void install() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @AfterEach
    public void setDown() {
        if (driver != null) driver.quit();
        log.info("Driver closed");
    }

    @Test
    public void test_1_QuantityTestCards() {
        driver = WebDriverFactory.create(WebDriverFactory.DriverManagerType.CHROME);
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("Driver started");
        driver.get(settings.get("url"));
        int link = new MainPage(driver).menuClick(toInt(settings.get("number_testing"))).getCards().stream().toList().size();
        Assertions.assertEquals(String.valueOf(link), settings.get("test_cards"));
        log.info("Количество карточек = " + link);
    }

    @Test
    public void test_2_InfoCourseTestCard()  {
        driver = WebDriverFactory.create(WebDriverFactory.DriverManagerType.CHROME);
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("Driver started");
        driver.get(settings.get("url"));
        CoursePage page = new MainPage(driver).menuClick(toInt(settings.get("number_testing"))).getCards().get(toInt(settings.get("number_card_of_testing"))).click(driver);
        log.info(" Длительность = " + page.getDuration());
        log.info(" Дата начала = " + page.getStartFrom());
        log.info(" Название = " + page.getTitle());
        log.info(" Описание = " + page.getDescriptionTest());
        log.info(" Формат = " + page.formatTest());
        Assertions.assertEquals(page.getDuration(), Prop.duration);
        Assertions.assertEquals(page.getStartFrom(), Prop.startFrom);
        Assertions.assertEquals(page.getTitle(), Prop.title);
        Assertions.assertEquals(page.getDescriptionTest(), Prop.description);
        Assertions.assertEquals(page.formatTest(), Prop.format);
    }

    @Test
    public void test_3_CheckCalendarEvents() {
        driver = WebDriverFactory.create(WebDriverFactory.DriverManagerType.CHROME);
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("Driver started");
        driver.get(settings.get("url"));
        List<Date> dates = new MainPage(driver).menuClickCalendar().getDates().stream().toList();
        for (Date date : dates) {
            Date date1 = new Date();
            Assertions.assertTrue(date1.before(date) || date1.equals(date));
        }
    }

    @Test
    public void test_4_CheckOpenWebinars() {
        driver = WebDriverFactory.create(WebDriverFactory.DriverManagerType.CHROME);
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("Driver started");
        driver.get(settings.get("url"));
        List<String> names = new MainPage(driver).menuClickCalendarOpenWebinar().getNamesOpenWebinar().stream().toList();

        for (String name : names) {
            String name1 = "Открытый вебинар";
            Assertions.assertEquals(name, name1);
        }
    }
}