package com.tqs;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
@ExtendWith(SeleniumJupiter.class)
public class BuyFlightTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void buyFlight() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isPageOpened());
        homePage.clickOnFindFlights();

        ChooseFlight chooseFlight = new ChooseFlight(driver);
        assertTrue(chooseFlight.is2ndPageOpened());
        chooseFlight.clickOnChooseFlight();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
