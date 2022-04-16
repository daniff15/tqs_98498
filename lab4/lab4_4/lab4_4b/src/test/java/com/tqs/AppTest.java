package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class AppTest {
  
  @Test
  public void seleniumTest(@DockerBrowser(type = CHROME) WebDriver driver) {
  
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
    driver.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
    
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Rikipalooza");
    
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("123 Main St.");
    
    driver.findElement(By.id("city")).sendKeys("Anytown");

    assertEquals("Your flight from TLV to SFO has been reserved.", driver.findElement(By.cssSelector("h2")).getText());
    
    driver.findElement(By.cssSelector(".btn-primary")).click();

    assertEquals("Thank you for your purchase today!", driver.findElement(By.cssSelector("h1")).getText());

    assertEquals("BlazeDemo Confirmation", driver.getTitle());
  }
  
}