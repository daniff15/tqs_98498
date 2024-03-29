package com.tqs;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
@ExtendWith(SeleniumJupiter.class)
public class BuyFlightTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @Test
    public void buyFlight() {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isPageOpened());
        homePage.clickOnFindFlights();

        ChooseFlight chooseFlight = new ChooseFlight(driver);
        assertTrue(chooseFlight.is2ndPageOpened());
        chooseFlight.clickOnChooseFlight();

        Informations informations = new Informations(driver);
        assertTrue(informations.is3rdPageOpened());
        informations.setName("Dani");
        informations.setAddress("McDonalds");
        informations.setCity("Aveiro");
        informations.clickOnPurchaseFlight();

        FinalPage finalPage = new FinalPage(driver);
        assertTrue(finalPage.isFinalPageOpened());

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
