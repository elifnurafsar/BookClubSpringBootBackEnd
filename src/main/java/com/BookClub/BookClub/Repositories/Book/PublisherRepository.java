package com.BookClub.BookClub.Repositories.Book;

import com.BookClub.BookClub.Entities.Books.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface PublisherRepository  extends JpaRepository<Publisher, UUID> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO \"publisher\"(name, website, phone, address) VALUES" +
            " (?1, ?2, ?3, ?4)", nativeQuery = true)
    int insertPublisher(String name, String website,String phone, String address);

    //get by name
    @Query(value = "SELECT * FROM \"publisher\" WHERE LOWER(\"name\") like %?1%  ORDER BY name LIMIT 10", nativeQuery = true)
    List<Publisher> findByName(String name);

    //get all publisher names
    @Query(value = "SELECT \"name\" FROM \"publisher\" ORDER BY name ASC", nativeQuery = true)
    List<String> getPublisherNames();
}