package com.BookClub.BookClub.Mapper.Book;
import com.BookClub.BookClub.Entities.Books.Publisher;
import com.BookClub.BookClub.Response.Book.PublisherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherResponse entityToResponse(Publisher publisher);

    List<PublisherResponse> entityListToResponseList(List<Publisher> publisher);
}
