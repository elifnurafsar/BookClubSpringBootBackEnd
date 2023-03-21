package com.BookClub.BookClub.Entities.Books;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Author", schema = "public")
@Data
@NoArgsConstructor
public class Author {

    @Id
    @Column(name = "id", unique = true, updatable = false, length=16)
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID ID;
    @Column(name = "name", columnDefinition = "text", nullable = false)
    private String name;
    @Column(name = "website", columnDefinition = "text", nullable = false)
    private String website;
    @Column(name = "biography", columnDefinition = "text")
    private String biography;

}
