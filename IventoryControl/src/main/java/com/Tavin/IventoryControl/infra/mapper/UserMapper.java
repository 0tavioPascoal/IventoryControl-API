package com.Tavin.IventoryControl.infra.mapper;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static final UserMapper INSTANCE = new UserMapper();
    public User toUser(UserRequestDto userRequestDto) {
        User user = new User();

        user.setUsername(userRequestDto.username());
        user.setPassword(userRequestDto.password());
        user.setEmail(userRequestDto.email());

        return user;
    }
}
