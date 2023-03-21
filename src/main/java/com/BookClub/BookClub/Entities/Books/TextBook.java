package com.BookClub.BookClub.Entities.Books;

import java.util.UUID;

public class TextBook extends Book {

    private static int discount=0;

    public TextBook(String ISBN, String title, String genre, int year, double price, String type, Author author, Publisher publisher, String description) {
        super(ISBN, title, genre, year, price, "text book", author, publisher,description);
        this.setPrice(price - (price * discount / 100));
    }

    public static void setDiscount(int discount) {
        TextBook.discount = discount;
    }

    public static int getDiscount() {
        return discount;
    }

}
