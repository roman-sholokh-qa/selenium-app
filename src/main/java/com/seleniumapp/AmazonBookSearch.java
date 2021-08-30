package com.seleniumapp;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Book> getBooksFromSearchPage() {
        int numberOfElements = webDriver.findElements(By.xpath("//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')]")).size();
        System.out.println(numberOfElements);

        List<Book> books = new ArrayList<>();

        String titleXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']";
        String authorsXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//div[@class='a-section a-spacing-none']/div[@class='a-row a-size-base a-color-secondary']";
        String priceXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//div[@class='a-section a-spacing-none a-spacing-top-small']//span[@class='a-price']//span[@class='a-offscreen']";
        String bestSellerXpath = "//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][%d]//span[contains(text(), 'Best Seller')]";

        for (int i = 1; i <= numberOfElements; i++) {
            Book book = new Book();

            try {
                book.setTitle(webDriver.findElement(By.xpath(String.format(titleXpath, i))).getAttribute("outerText"));
            } catch (NoSuchElementException e) {
                book.setTitle("no title found");
            }

            try {
                book.setAuthor(Stream.of(webDriver.findElement(By.xpath(String.format(authorsXpath, i))).getAttribute("outerText"))
                        .map(s -> s.chars().filter(ch -> ch == '|').count() <= 1 ? s.substring(0, s.lastIndexOf('|')) : s.substring(s.indexOf('|') + 1, s.lastIndexOf('|')))
                        .map(s -> s.replaceFirst("by", ""))
                        .map(s -> s.trim())
                        .collect(Collectors.joining()));
            } catch (Exception e) {
                book.setAuthor("no author found");
            }

            try {
                book.setPrice(webDriver.findElement(By.xpath(String.format(priceXpath, i))).getAttribute("outerText"));
            } catch (Exception e) {
                book.setPrice("no price found");
            }

            book.setBestseller(webDriver.findElements(By.xpath(String.format(bestSellerXpath, i))).size() != 0);

            books.add(book);
        }

        return books;
    }

    public void close() {
        webDriver.close();
    }
}
