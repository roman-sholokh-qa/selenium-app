package com.seleniumapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonBookSearch {
    private final By searchBarLocator = By.id("twotabsearchtextbox");
    private final By searchSubmitButtonLocator = By.id("nav-search-submit-button");

    private final String amazonUrl = "https://www.amazon.com/";

    private final WebDriver webDriver;

    public AmazonBookSearch(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(amazonUrl);

        if (!"Amazon.com. Spend less. Smile more.".equals(webDriver.getTitle())) {
            throw new IllegalStateException("Not a Amazon Search page");
        }
    }

    public void typeSearchQueryInSearchBar(String query) {
        webDriver.findElement(searchBarLocator).sendKeys(query);
    }

    public void submitSearchQuery() {
        webDriver.findElement(searchSubmitButtonLocator).submit();
    }


}
