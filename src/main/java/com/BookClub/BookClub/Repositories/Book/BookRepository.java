package com.BookClub.BookClub.Repositories.Book;

import com.BookClub.BookClub.DTO.Book.BookDTO;
import com.BookClub.BookClub.Entities.Books.Book;
import com.BookClub.BookClub.Entities.Util.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//implement data access layers, create requests for database
@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO book (isbn, title, author, publisher, genre, price, year, typeval, created_at, description) VALUES\n" +
            "(?1, ?2, \n" +
            " (SELECT id FROM \"author\" WHERE LOWER(\"name\") like %?3%), \n" +
            " (SELECT id FROM \"publisher\" WHERE LOWER(\"name\") like %?4%),\n" +
            " ?5, ?6, ?7, ?8, NOW(), ?9);", nativeQuery = true)
    int insertBook(String ISBN, String title, String author, String publisher, String genre, double price, int year, String typeval, String description);

    @Query(value = "SELECT * FROM \"orders\" AS O WHERE O.customer_id IN " +
            "(SELECT id FROM users WHERE e_mail = ?1) " +
            "ORDER BY created_at DESC", nativeQuery = true)
    List<Order> getOrderByCustomerEMail(String e_mail);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM \"book\" WHERE \"isbn\" = ?1", nativeQuery = true)
    int deleteBook(String ISBN);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE \"book\" SET " +
            "author = (SELECT id from \"author\" WHERE name like %:#{#bookDTO.author}%), " +
            "publisher = (SELECT id from \"publisher\" WHERE name like %:#{#bookDTO.publisher}%), " +
            "title = :#{#bookDTO.title}, " +
            "genre = :#{#bookDTO.genre}, " +
            "year = :#{#bookDTO.year}, " +
            "price = :#{#bookDTO.price}, " +
            "typeval = :#{#bookDTO.type}, " +
            "description = :#{#bookDTO.description} " +
            "WHERE isbn = :#{#bookDTO.isbn} ", nativeQuery = true)
    int updateBook(@Param("bookDTO") BookDTO bookDTO);

    @Query(value = "SELECT * FROM \"book\" ORDER BY created_at DESC", nativeQuery = true)
    List<Book> getBooks();

    @Query(value = "SELECT * FROM \"book\" ORDER BY created_at DESC LIMIT 20", nativeQuery = true)
    List<Book> getRecentBooks();

    @Query(value = "SELECT * FROM \"book\" WHERE title ilike %?1% or typeval ilike %?1%", nativeQuery = true)
    List<Book> findBookByTitleOrType(String title_or_type);

    @Query(value = "SELECT * FROM \"book\" WHERE \"isbn\" = ?1", nativeQuery = true)
    Optional<Book> findBookByISBN(String ISBN);

    @Query(value = "SELECT * FROM \"book\" ORDER BY price DESC", nativeQuery = true)
    List<Book> getCheapestBooks();

    @Query(value = "SELECT * FROM \"book\" ORDER BY price ASC LIMIT 20", nativeQuery = true)
    List<Book> getExpensiveBooks();
}
