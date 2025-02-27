package com.Tavin.IventoryControl.infra.mappers.user;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import com.Tavin.IventoryControl.infra.dtos.user.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User UserModelMapper(UserRequestDto userRequestDto);

    UserResponseDto UserResponseDtoMapper(User user);



}
