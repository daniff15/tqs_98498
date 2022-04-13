package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BuyFlight {

    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @When("I choose a departure city with name {string}")
    public void i_choose_a_departure_city_with_name(String departure) {
        driver.findElement(By.cssSelector("[name='fromPort']")).sendKeys(departure);
    }
    
    @When("I choose a destination city with name {string}")
    public void i_choose_a_destination_city_with_name(String destination) {
        driver.findElement(By.cssSelector("[name='toPort']")).sendKeys(destination);
    }

    @When("I click Find Flights")
    public void i_click_find_flights() {
        driver.findElement(By.tagName("input")).click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String message) {
        try {
            assertEquals(driver.findElement(By.tagName("h3")).getText(), message);

        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + message + "\" not available in results");
        }
    }

    @Then("I click Choose This Flight")
    public void i_click_choose_this_flight() {
        driver.findElement(By.tagName("input")).click();
    }

    @Then("my name is {string}, my address is {string} and my city is {string}")
    public void my_name_is_my_address_is_and_my_city_is(String name, String address, String city) {
        driver.findElement(By.id("inputName")).sendKeys(name);
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("city")).sendKeys(city);
    }

    @Then("I click Purchase This Flight")
    public void i_click_purchase_this_flight() {
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }

    @Then("I should see the successful message {string}")
    public void i_should_see_the_successful_message(String message) {
        try {
            assertEquals(driver.findElement(By.tagName("h1")).getText(), message);

        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + message + "\" not available in results");
        } finally {
            driver.quit();
        }
    }

}
