package com.Tavin.IventoryControl.infra.mappers;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperUser {

    User toUser(UserRequestDto userRequestDto);
}
