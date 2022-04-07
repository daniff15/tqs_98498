package com.tqs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ChooseFlight {
    @FindBy(tagName = "h3")
    WebElement head;

    private WebDriver driver;

    @FindBy(how = How.LINK_TEXT, using = "Choose This Flight")
    private WebElement chooseFlightButton;

    public ChooseFlight(WebDriver driver) {
        this.driver = driver;

        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnChooseFlight() {
        chooseFlightButton.click();
    }

    public boolean is2ndPageOpened() {
        return head.getText().toString().contains("Flights from Paris to Buenos Aires:");
    }
}
