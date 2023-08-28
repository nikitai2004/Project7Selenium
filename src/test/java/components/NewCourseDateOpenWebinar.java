package components;

import org.openqa.selenium.WebElement;

public class NewCourseDateOpenWebinar {
    private WebElement name;

    public NewCourseDateOpenWebinar(WebElement element) {
        name = element;
    }

    public String getNameOpenWebinar() {
        return name.getText();
    }
}