package base;

import config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.TestingUtility;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseDriver {
    public static WebDriver driver;
    public static Properties property;
    public static WebDriverWait wait;
    public static Configuration config;
    protected static Logger logger = LogManager.getLogger();

    public static void creatingTheSession() {
        driver = null;
        config = new Configuration();
        property = config.getProperties();
        String browserName = property.getProperty("BROWSER");

        switch (browserName) {
            case "CHROME": {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + property.getProperty("CHROME_PATH"));
                System.out.println("Path of the driver "+ System.getProperty("user.dir") + property.getProperty("CHROME_PATH"));
                driver = new ChromeDriver();
                logger.info("Chrome Driver is launched");
                break;
            }
            case "FIREFOX": {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + property.getProperty("GECKO_PATH"));
                driver = new FirefoxDriver();
                logger.info("Firefox Driver is launched");
                break;
            }
            case "MAC_CHROME": {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + property.getProperty("MAC_CHROME_DRIVER_PATH"));
                driver = new ChromeDriver();
                logger.info("Chrome Driver for MAC is launched");
                break;
            }

        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestingUtility.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestingUtility.IMPLICIT_WAIT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestingUtility.WEB_DRIVER_WAIT));
    }

    public void navigateToGoogleUrl() {
        driver.get(property.getProperty("GOOGLE_URL"));
        logger.info("Webdriver is launched with "+property.getProperty("GOOGLE_URL"));
    }
    public void navigateToBingUrl() {
        driver.get(property.getProperty("BING_URL"));
        logger.info("Webdriver is launched with "+property.getProperty("BING_URL"));
    }


}