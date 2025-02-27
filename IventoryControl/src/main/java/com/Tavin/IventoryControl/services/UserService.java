package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import com.Tavin.IventoryControl.infra.dtos.user.UserResponseDto;
import com.Tavin.IventoryControl.infra.mappers.user.UserMapper;
import com.Tavin.IventoryControl.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto register(UserRequestDto userRequestDto) {
        User user = userMapper.UserModelMapper(userRequestDto);

        if(userRepository.existsById(user.getId())) {
            throw new RuntimeException("User already exists");
        }

        var userSaved = userRepository.save(user);
        return userMapper.UserResponseDtoMapper(userSaved);
    }


    public Object updateUser(String id, UserRequestDto userRequestDto) {
        Optional<User> optionalId = userRepository.findById(UUID.fromString(id));

        if(optionalId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

       var user = optionalId.get();
        user.setEmail(userRequestDto.email());
        user.setUsername(userRequestDto.username());
        user.setPassword(userRequestDto.password());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


}

