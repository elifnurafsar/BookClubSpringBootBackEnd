package com.BookClub.BookClub.Mapper.Book;

import com.BookClub.BookClub.Entities.Books.Author;
import com.BookClub.BookClub.Response.Book.AuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorResponse entityToResponse(Author author);

    List<AuthorResponse> entityListToResponseList(List<Author> author);
}
