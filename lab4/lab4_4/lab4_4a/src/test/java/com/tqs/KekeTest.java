package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class KekeTest 
{
    private HtmlUnitDriver driver;

    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
      driver = new HtmlUnitDriver();
      js = (JavascriptExecutor) driver;
    }

    @Test
    public void seleniumTest() {
  
        driver.get("https://blazedemo.com/");

        assertEquals("BlazeDemo", driver.getTitle());
        {
          WebElement dropdown = driver.findElement(By.name("fromPort"));
          dropdown.findElement(By.xpath("//option[. = 'Paris']")).click();
        }

        {
          WebElement dropdown = driver.findElement(By.name("toPort"));
          dropdown.findElement(By.xpath("//option[. = 'Rome']")).click();
        }

        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();

        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Dani");

        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("69 Mc");

        driver.findElement(By.id("city")).sendKeys("Aveiro");

        assertEquals("Your flight from TLV to SFO has been reserved.", driver.findElement(By.cssSelector("h2")).getText());

        driver.findElement(By.cssSelector(".btn-primary")).click();

        assertEquals("Thank you for your purchase today!", driver.findElement(By.cssSelector("h1")).getText());

        assertEquals("BlazeDemo Confirmation", driver.getTitle());
    }
  
    @AfterEach
    public void tearDown() {
      driver.quit();
    }
}
