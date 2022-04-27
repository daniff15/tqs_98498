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

public class WebPageTest {

        private WebDriver driver;
        JavascriptExecutor js;

        @BeforeEach
        void setup() {
                driver = new ChromeDriver();
                js = (JavascriptExecutor) driver;
        }

        @Test
        public void countryDataTest() throws InterruptedException {

                // ! AQUI À MEDIDA Q OS DIAS PASSAM OS TESTES MUDAM

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

                Thread.sleep(2000);

                assertEquals("30338697",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(1) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("0",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(2) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("662802",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(3) > div > p:nth-child(2)"))
                                                .getText());

                assertEquals("29675895",
                                driver.findElement(
                                                By.cssSelector("body > div:nth-child(2) > div > div > div:nth-child(4) > div > p:nth-child(2)"))
                                                .getText());

        }

        @Test
        public void dateDataTest() throws InterruptedException {
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
        public void dateCoutryDataTest() throws InterruptedException {
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
                Thread.sleep(2000);

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

        @AfterEach
        void teardown() {
                driver.quit();
        }

}
