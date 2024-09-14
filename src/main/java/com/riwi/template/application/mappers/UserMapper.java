package com.riwi.template.application.mappers;

import com.riwi.template.application.dtos.reponses.UserResponse;
import com.riwi.template.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserResponse userToUserResponse(User user);
}
