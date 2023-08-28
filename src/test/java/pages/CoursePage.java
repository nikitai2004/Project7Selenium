package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursePage extends BasePage {

    @FindBy(css = "h1.sc-1og4wiw-0.sc-s2pydo-1.bojQOq.diGrSa")
    private WebElement title;

    @FindBy(css = ".course-header2__subtitle")
    private WebElement description;

    @FindBy(xpath = "//p[contains(text(),'Онлайн')]")
    private WebElement formatTest;

    @FindBy(xpath = "//div[@class='sc-1ddwpfq-1 hnlXxp sc-s2pydo-5 iHjMxQ']//p[1]")
    private WebElement descriptionTest;

    @FindBy(css = ".sc-x072mc-0.sc-3cb1l3-1.hOtCic.galmep .sc-3cb1l3-3.jeNzke .sc-3cb1l3-4.kGoYMV:first-child")
    private WebElement startFrom;

    @FindBy(css = ".sc-x072mc-0.sc-3cb1l3-1.hOtCic.galmep .sc-3cb1l3-3.jeNzke .sc-3cb1l3-4.kGoYMV:nth-child(2)")
    private WebElement duration;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }
    public String getDescriptionTest() {
        return descriptionTest.getText();
    }

    public String formatTest() {
        return formatTest.getText();
    }

    public String getStartFrom() {
        return startFrom.getText();
    }

    public String getDuration() {
        return duration.getText();
    }
}