package com.tqs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(tagName = "h1") WebElement head;

    @FindBy(tagName = "fromPort") WebElement fromPort;

    @FindBy(tagName = "toPort") WebElement toPort;

    @FindBy(className="btn-primary") private WebElement findFlightsButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnFindFlights() {
        findFlightsButton.click();
    }

    public boolean isPageOpened() {
        return head.getText().toString().contains("Welcome to the Simple Travel Agency!");
    }

}
