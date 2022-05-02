package com.example.demo.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.junit.jupiter.api.extension.ExtendWith;
import io.github.bonigarcia.seljup.SeleniumJupiter;     

@ExtendWith(SeleniumJupiter.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WebPageTest {

        private WebDriver driver;
        JavascriptExecutor js;

        @BeforeEach
        void setup() {
                driver = new ChromeDriver();
                js = (JavascriptExecutor) driver;
        }

        @Test
        @Order(1)
        void countryDataTest() throws InterruptedException {

                driver.get("http://localhost:8080/index");
                driver.manage().window().setSize(new Dimension(1866, 1053));
                {
                        WebElement dropdown = driver.findElement(By.id("name"));
                        dropdown.findElement(By.xpath("//option[. = 'Brazil']")).click();
                }
                assertEquals("Search for information about Covid-19 in a country...",
                                driver.findElement(By.xpath("/html/body/div[2]/div/h3")).getText());

                driver.findElement(By.id("name")).click();
                driver.findElement(By.id("btn")).click();

        }

        @Test
        @Order(2)
        void dateDataTest() throws InterruptedException {
                driver.get("http://localhost:8080/date");
                driver.manage().window().setSize(new Dimension(1866, 1053));

                assertEquals("Search for information about Covid-19 in a certain date worldwide...",
                                driver.findElement(By.xpath("/html/body/div[2]/div/h3")).getText());

                driver.findElement(By.id("input")).click();
                driver.findElement(By.id("input")).sendKeys("1892020");
                driver.findElement(By.id("btn")).click();

                assertEquals("30406197",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(1) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("20683110",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(2) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("950520",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(3) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("8772567",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(4) > div > p:nth-child(2)"))
                                                .getText());
        }

        @Test
        @Order(3)
        void dateCoutryDataTest() throws InterruptedException {
                driver.get("http://localhost:8080/countrydate");
                driver.manage().window().setSize(new Dimension(1866, 1053));

                assertEquals("Search for information about Covid-19 through date and country...",
                                driver.findElement(By.xpath("/html/body/div[2]/div/h3")).getText());

                driver.findElement(By.id("input")).click();
                driver.findElement(By.id("input")).sendKeys("2072020");

                {
                        WebElement dropdown = driver.findElement(By.id("countryName"));
                        dropdown.findElement(By.xpath("//option[. = 'Mexico']")).click();
                }

                driver.findElement(By.id("btn")).click();

                assertEquals("3486",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(1) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("2745",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(2) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("209",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(3) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("532",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(4) > div > p:nth-child(2)"))
                                                .getText());
        }


        @Test
        @Order(4)
        void cacheDataTest() throws InterruptedException {
                driver.get("http://localhost:8080/cache");
                driver.manage().window().setSize(new Dimension(1866, 1053));

                assertEquals("Cache",
                                driver.findElement(By.xpath("/html/body/div[2]/div/h3")).getText());

                driver.findElement(By.id("btn")).click();

                assertEquals("3",
                                driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/table/tbody/tr[1]/td")).getText());

                assertEquals("0",
                                driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/table/tbody/tr[2]/td")).getText());

                assertEquals("3",
                                driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/table/tbody/tr[3]/td")).getText());


        }



        @AfterEach
        void teardown() {
                driver.quit();
        }

}
