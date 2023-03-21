package com.BookClub.BookClub.DTO.Book;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BookDTO {
    private String isbn;
    private String title;
    private String genre;
    private int year;
    private double price;
    private String type;
    private String description;
    private String author;
    private String publisher;
}
