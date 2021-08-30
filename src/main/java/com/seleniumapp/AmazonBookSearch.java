package com.seleniumapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AmazonBookSearch {
    private final By searchBarLocator = By.id("twotabsearchtextbox");
    private final By searchSubmitButtonLocator = By.id("nav-search-submit-button");
    private final By searchDropdownBoxLocator = By.id("searchDropdownBox");

    private final String amazonUrl = "https://www.amazon.com/";

    private final WebDriver webDriver;

    public AmazonBookSearch(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(amazonUrl);

        if (!"Amazon.com. Spend less. Smile more.".equals(webDriver.getTitle())) {
            throw new IllegalStateException("Not a Amazon Search page");
        }
    }

    public void selectDepartmentFromDropdownDepartmentsBox(String department) {
        Select dropDepartment = new Select(webDriver.findElement(searchDropdownBoxLocator));
        dropDepartment.selectByVisibleText(department);
    }

    public void typeSearchQueryInSearchBar(String query) {
        webDriver.findElement(searchBarLocator).sendKeys(query);
    }

    public void submitSearchQuery() {
        webDriver.findElement(searchSubmitButtonLocator).submit();
    }

//    public void createElementList() {
////        String titleXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//h2/a/span";
////        String authorXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//div[@class='a-row']/a";
////        String priceXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//div[1]/div/div[1]/div[2]/a/span[1]/span[1]";
////
////
////        List<String> strings = new ArrayList<>();
////
////        for (int i = 1; i <= 16; i++) {
////            strings.add(webDriver.findElement(By.xpath(String.format(priceXpath, i))).getText());
////        }
////        for (String string : strings) {
////            System.out.println(string);
////        }
//
//
////        for (WebElement element : elementList) {
////            strings.add(element.findElement(By.xpath(String.format())).getText());
////        }
////
////
////
////        List<String> titles = webDriver.findElements(By.xpath("//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']"))
////                .stream()
////                .map(WebElement::getText)
////                .collect(Collectors.toList());
////
////        List<String> authors = webDriver.findElements(By.xpath("//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//div[@class='a-section a-spacing-none']/div[@class='a-row a-size-base a-color-secondary']"))
////                .stream()
////                .map(WebElement::getText)
////                .map(s -> s.replaceFirst("by", "").substring(0, s.lastIndexOf('|') - 2).trim())
////                .collect(Collectors.toList());
////
//////        System.out.println(webDriver.findElement(By.xpath("//div[@class='a-section a-spacing-none a-spacing-top-small']//span[@class='a-price']//span[@class='a-offscreen']")).getAttribute("outerText"));
////
////        List<String> prices = webDriver.findElements(By.xpath("//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//div[@class='a-section a-spacing-none a-spacing-top-small']//span[@class='a-price']//span[@class='a-offscreen']"))
////                .stream()
////                .map(e -> e.getAttribute("outerText"))
////                .collect(Collectors.toList());
//
//        List<Boolean> isBestsellers = new ArrayList<>();
//
//        String bestSellerXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//span[contains(text(), 'Best Seller')]";
//
//        for (int i = 1; i <= 16; i++) {
//            isBestsellers.add(webDriver.findElements(By.xpath(String.format(bestSellerXpath, i))).size() != 0);
//        }
//
////        boolean exists = webDriver.findElements( By.xpath("...") ).size() != 0;
//
//        for (Boolean b : isBestsellers) {
//            System.out.println(b);
//        }
//
////
////
////        for (String string : prices) {
////            System.out.println(string);
////        }
//
//
//
//    }

    public List<Book> getBooksFromSearchPage() {
        int numberOfElements = 16;

        List<Book> books = new ArrayList<>();

        String titleXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']";
        String authorsXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//div[@class='a-section a-spacing-none']/div[@class='a-row a-size-base a-color-secondary']";
        String priceXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//div[@class='a-section a-spacing-none a-spacing-top-small']//span[@class='a-price']//span[@class='a-offscreen']";
        String bestSellerXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//span[contains(text(), 'Best Seller')]";

        for (int i = 1; i <= numberOfElements; i++) {
            Book book = new Book();
//            book.setTitle(elementBuilder(titleXpath, i) != null ?  );

            book.setTitle(webDriver.findElement(By.xpath(String.format(titleXpath, i))).getAttribute("outerText"));
            book.setAuthor(webDriver.findElement(By.xpath(String.format(authorsXpath, i))).getAttribute("outerText"));
            book.setPrice(webDriver.findElement(By.xpath(String.format(priceXpath, i))).getAttribute("outerText"));
            book.setBestseller(webDriver.findElements(By.xpath(String.format(bestSellerXpath, i))).size() != 0);
            books.add(book);
        }

        return books;
    }


    private WebElement elementBuilder(String xpath, int i) {
        WebElement element = null;
        try {
            return element = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(driver -> driver.findElement(By.xpath(String.format(xpath, i))));

        } catch (Exception e) {
            return null;
        }

    }




}
