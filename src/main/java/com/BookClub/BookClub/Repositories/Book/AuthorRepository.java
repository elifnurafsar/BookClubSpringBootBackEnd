package com.BookClub.BookClub.Repositories.Book;

import com.BookClub.BookClub.Entities.Books.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO Author(name, website, biography) VALUES (:#{#author.name}, :#{#author.website}, :#{#author.biography})", nativeQuery = true)
    int insertAuthor(@Param("author") Author author);

    @Query(value = "SELECT * FROM author ORDER BY ID DESC LIMIT 10", nativeQuery = true)
    List<Author> getRecentAuthors();

    @Query(value = "SELECT \"name\" FROM author ORDER BY name ASC", nativeQuery = true)
    List<String> getAuthorNames();

    @Query(value = "SELECT * FROM \"author\" WHERE LOWER(\"name\") like %?1% ORDER BY name LIMIT 100", nativeQuery = true)
    List<Author> findByName(String name);
}