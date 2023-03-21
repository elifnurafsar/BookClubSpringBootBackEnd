package com.BookClub.BookClub.Response.Book;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BookResponse {
    //response entity for simplification
    private String isbn;
    private String title;
    private String genre;
    private int year;
    private double price;
    private String type;
    private Timestamp created_at;
    private String description;
    private String author;
    private String publisher;
}
