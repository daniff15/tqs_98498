package com.tqs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Informations {
    @FindBy(tagName = "h2")
    WebElement head;

    @FindBy(id = "inputName")
    WebElement name;

    @FindBy(id = "address")
    WebElement address;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(how = How.LINK_TEXT, using = "Purchase Flight")
    private WebElement purchaseFlightsButton;

    public Informations(WebDriver driver) {
        this.driver = driver;

        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void setName(String name) {
        name.clear();
        name.sendKeys(name);
    }

    public void setAddress(String address) {
        address.clear();
        address.sendKeys(address);
    }

    public void setCity(String city) {
        city.clear();
        city.sendKeys(city);
    }

    public void clickOnPurchaseFlight() {
        purchaseFlightsButton.click();
    }

    public boolean is3rdPageOpened() {
        return head.getText().toString().contains("Your flight from TLV to SFO has been reserved.");
    }
}
