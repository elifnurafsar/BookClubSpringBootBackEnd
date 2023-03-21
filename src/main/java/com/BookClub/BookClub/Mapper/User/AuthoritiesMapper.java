package com.BookClub.BookClub.Mapper.User;

import com.BookClub.BookClub.Entities.User.Authorities;
import com.BookClub.BookClub.Response.User.AuthorityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthoritiesMapper {
    AuthoritiesMapper INSTANCE = Mappers.getMapper(AuthoritiesMapper.class);

    AuthorityResponse entityToResponse(Authorities authorities);

    List<AuthorityResponse> entityListToResponseList(List<Authorities> authorities);
}
