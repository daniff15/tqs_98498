package com.tqs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseFlight {
    private WebDriver driver;

    @FindBy(tagName = "h3")
    WebElement head;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[1]/input")
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
