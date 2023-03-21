package com.BookClub.BookClub.Entities.Books;

public class NovelBook extends Book {

    private static int discount=0;

    public NovelBook(String ISBN, String title, String genre, int year, double price, String type, Author author, Publisher publisher, String description) {
        super(ISBN, title, genre, year, price, "novel", author, publisher, description);
        this.setPrice(price - (price * discount / 100));
    }

    public static void setDiscount(int discount) {
        NovelBook.discount = discount;
    }

    public static int getDiscount() {
        return discount;
    }
}
