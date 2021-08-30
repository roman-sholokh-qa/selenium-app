package com.seleniumapp;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String price;
    private boolean isBestseller;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return price;
    }

    public boolean isBestseller() {
        return isBestseller;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBestseller(boolean bestseller) {
        isBestseller = bestseller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isBestseller == book.isBestseller && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, price, isBestseller);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", isBestseller=" + isBestseller +
                '}';
    }
}
