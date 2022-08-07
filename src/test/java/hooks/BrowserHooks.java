package hooks;

import base.BaseDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;

public class BrowserHooks extends BaseDriver {
    @Before()
    public void init(Scenario scenario) {
            logger.info("Scenario: "+scenario.getName()+" is started");
            creatingTheSession();
    }

    @After()
    public void teardown(Scenario scenario){
            logger.info("Scenario: "+scenario.getName()+" is completed");
            driver.quit();
    }
}
