package com.seleniumapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Starter {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/WebDriver/bin/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

//        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
//
//        googleSearchPage.typeSearchQuery("Java");
//        googleSearchPage.submitSearchQuery();
//
//        GoogleSearchPage anotherGoogleSearchPage = new GoogleSearchPage(driver);
//
//        anotherGoogleSearchPage.typeSearchQueryAndSubmit("Java");


        AmazonBookSearch amazonBookSearch = new AmazonBookSearch(driver);

        amazonBookSearch.selectDepartmentFromDropdownDepartmentsBox("Books");
        amazonBookSearch.typeSearchQueryInSearchBar("Java");
        amazonBookSearch.submitSearchQuery();
        amazonBookSearch.getBooksFromSearchPage()
                .forEach(System.out::println);

        amazonBookSearch.close();
    }
}
