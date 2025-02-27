package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import com.Tavin.IventoryControl.infra.mappers.MapperUser;
import com.Tavin.IventoryControl.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MapperUser mapperUser;

    public User register(UserRequestDto userRequestDto) {
       var user = mapperUser.toUser(userRequestDto);


        return userRepository.save(user);
    }

    public User UpdateUser(@RequestParam String id, UserPutRequestDto userPutRequestDto) {

        User user = getUserById(id);
        user.setUsername(userPutRequestDto.username());
        user.setPassword(userPutRequestDto.password());
        user.setEmail(userPutRequestDto.email());
        return userRepository.save(user);
    }


    public User getUserById(String id) {
        UUID uuid = UUID.fromString(id);
        return userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

