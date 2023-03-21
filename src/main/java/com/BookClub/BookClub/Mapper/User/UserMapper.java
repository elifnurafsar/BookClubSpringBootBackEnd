package com.BookClub.BookClub.Mapper.User;

import com.BookClub.BookClub.DTO.User.UserDTO;
import com.BookClub.BookClub.Entities.User.User;
import com.BookClub.BookClub.Response.User.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse entityToResponse(User user);

    List<UserResponse> entityListToResponseList(List<User> users);

    UserResponse identifierToResponse(UserDTO userDTOtoResponse);

}
