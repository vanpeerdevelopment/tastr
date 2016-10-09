package eu.tastr.e2e.configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class SelenideConfiguration {

    public static final int SELENIDE_TIMEOUT = 10000;

    private SelenideConfiguration() {}

    public static void configure() {
        WebDriverRunner.getAndCheckWebDriver().manage().window().maximize();
        resetSelenideTimeouts();
    }

    public static void resetSelenideTimeouts() {
        setSelenideTimeouts(SELENIDE_TIMEOUT);
    }

    public static void setSelenideTimeouts(int millis) {
        Configuration.timeout = millis;
        Configuration.collectionsTimeout = millis;
    }
}
