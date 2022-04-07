package com.tqs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    private static String PAGE_URL = "https://blazedemo.com/";

    @FindBy(tagName = "h1")
    WebElement head;

    @FindBy(tagName = "fromPort")
    WebElement fromPort;

    @FindBy(tagName = "toPort")
    WebElement fromPort;

    @FindBy(how = How.LINK_TEXT, using = "Find Flights")
    private WebElement findFlightsButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnFindFlights() {
        findFlightsButton.click();
    }

    public boolean isPageOpened() {
        return head.getText().toString().contains("Welcome to the Simple Travel Agency!");
    }

}
