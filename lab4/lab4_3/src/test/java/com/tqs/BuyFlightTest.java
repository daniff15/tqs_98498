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
    public void shouldAnswerWithTrue() {
        HomePage homePage = new HomePage(driver);

        assertTrue(homePage.isPageOpened());
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
