package org.example.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    protected static final String BASE_URL = "https://qa-desk.education-services.ru";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\adminpc\\Desktop\\chromedriver-win64\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.baseUrl = BASE_URL;
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";

        Selenide.open(BASE_URL);
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}