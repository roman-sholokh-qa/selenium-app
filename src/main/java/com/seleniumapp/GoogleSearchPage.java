package com.seleniumapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {
    private final By searchBarLocator = By.name("q");
    private final By buttonGoogleSearchLocator = By.name("btnK");
    private final String googleUrl = "http://google.com";

    private final WebDriver webDriver;

    public GoogleSearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(googleUrl);

        if (!"Google".equals(webDriver.getTitle())) {
            throw new IllegalStateException("Not a Google Search page");
        }
    }

    public void typeSearchQuery(String query) {
        webDriver.findElement(searchBarLocator).sendKeys(query);
    }

    public void submitSearchQuery() {
        webDriver.findElement(buttonGoogleSearchLocator).submit();
    }

    public void typeSearchQueryAndSubmit(String query) {
        typeSearchQuery(query);
        submitSearchQuery();
    }

}
