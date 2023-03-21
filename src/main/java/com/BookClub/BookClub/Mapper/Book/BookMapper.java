package com.BookClub.BookClub.Mapper.Book;

import com.BookClub.BookClub.Entities.Books.Author;
import com.BookClub.BookClub.Entities.Books.Book;
import com.BookClub.BookClub.Entities.Books.Publisher;
import com.BookClub.BookClub.Response.Book.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookResponse entityToResponse(Book book);

    List<BookResponse> entityListToResponseList(List<Book> book);

    static String map(Publisher publisher) {
        return publisher.getName();
    }

    static String map(Author author) {
        return author.getName();
    }
}

