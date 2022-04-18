package com.tqs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Informations {
    private WebDriver driver;
    
    @FindBy(tagName = "h2") WebElement head;

    @FindBy(id = "inputName") WebElement name;

    @FindBy(id = "address") WebElement address;

    @FindBy(id = "city") WebElement city;

    @FindBy(xpath = "/html/body/div[2]/form/div[11]/div/input") private WebElement purchaseFlightsButton;

    public Informations(WebDriver driver) {
        this.driver = driver;

        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void setName(String nameStr) {
        name.clear();
        name.sendKeys(nameStr);
    }

    public void setAddress(String addressStr) {
        address.clear();
        address.sendKeys(addressStr);
    }

    public void setCity(String cityStr) {
        city.clear();
        city.sendKeys(cityStr);
    }

    public void clickOnPurchaseFlight() {
        purchaseFlightsButton.click();
    }

    public boolean is3rdPageOpened() {
        return head.getText().toString().contains("Your flight from TLV to SFO has been reserved.");
    }
}
