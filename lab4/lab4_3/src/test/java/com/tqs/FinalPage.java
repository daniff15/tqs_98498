package com.tqs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FinalPage {
    private WebDriver driver;
    
    @FindBy(tagName = "h1") WebElement head;

    public FinalPage(WebDriver driver) {
        this.driver = driver;

        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean isFinalPageOpened() {
        return head.getText().toString().contains("Thank you for your purchase today!");
    }
}
