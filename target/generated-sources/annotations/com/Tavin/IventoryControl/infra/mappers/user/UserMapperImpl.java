package com.Tavin.IventoryControl.infra.mappers.user;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import com.Tavin.IventoryControl.infra.dtos.user.UserResponseDto;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-26T14:12:07-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User UserModelMapper(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( userRequestDto.username() );
        user.password( userRequestDto.password() );
        user.email( userRequestDto.email() );

        return user.build();
    }

    @Override
    public UserResponseDto UserResponseDtoMapper(User user) {
        if ( user == null ) {
            return null;
        }

        UUID id = null;
        String username = null;
        String email = null;
        String password = null;

        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();

        UserResponseDto userResponseDto = new UserResponseDto( id, username, email, password );

        return userResponseDto;
    }
}
