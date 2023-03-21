package com.BookClub.BookClub.Entities.Books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

//https://medium.com/analytics-vidhya/jpa-hibernate-entity-inheritance-1f6aa7ea2eea

@Entity
@Table(name = "Book", schema = "public")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Column(name = "Title", columnDefinition = "text", nullable = false)
    private String title;
    @Column(name = "genre", columnDefinition = "text",  nullable = false)
    private String genre;
    @Column(name = "year", columnDefinition = "text",  nullable = false)
    private int year;
    @Column(name = "price", columnDefinition = "text",  nullable = false)
    private double price;
    @Column(name = "typeval", columnDefinition = "text", nullable = false)
    private String type;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "description", columnDefinition = "text")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Author author_o;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Publisher publisher_o;

    public Book(String ISBN, String title, String genre, int year, double price, String type, Author author, Publisher publisher, String description){
        this.isbn = ISBN;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.price = price;
        this.type=type;
        this.author_o = author;
        this.publisher_o = publisher;
        this.description = description;
    }

    public Author getAuthor() { return this.author_o; }

    public Publisher getPublisher() { return this.publisher_o; }

}

