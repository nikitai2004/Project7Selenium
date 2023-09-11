package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewCourseDate {
    private final WebElement date;

    public NewCourseDate(WebElement element) {
        date = element;
    }

    public String getCardDate() {
        return date.findElement(By.cssSelector(".dod_new-event__time > span:nth-child(1)>span:nth-child(2)")).getText();
    }
}