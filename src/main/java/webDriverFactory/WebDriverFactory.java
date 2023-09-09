package webDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    public static WebDriver create(@org.jetbrains.annotations.NotNull DriverManagerType browserName) {
        return switch (browserName.getDriver()) {
            case "ChromeDriver" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case "FirefoxDriver" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case "EdgeDriver" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            default -> null;
        };
    }

    public static WebDriver create(DriverManagerType browserName, MutableCapabilities wdOptions) {
        return switch (browserName.getDriver()) {
            case "ChromeDriver" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver((ChromeOptions) wdOptions);
            }
            case "FirefoxDriver" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver((FirefoxOptions) wdOptions);
            }
            case "EdgeDriver" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver((EdgeOptions) wdOptions);
            }
            default -> null;
        };
    }

    public enum DriverManagerType {
        CHROME("ChromeDriver"), FIREFOX("FirefoxDriver"), EGGE("EdgeDriver");
        private final String driverName;

        DriverManagerType(String driverName) {
            this.driverName = driverName;
        }

        public String getDriver() {
            return driverName;
        }
    }
}