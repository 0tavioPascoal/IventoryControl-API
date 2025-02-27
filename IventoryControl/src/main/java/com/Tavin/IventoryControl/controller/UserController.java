package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import com.Tavin.IventoryControl.infra.dtos.user.UserResponseDto;
import com.Tavin.IventoryControl.infra.mappers.user.UserMapper;
import com.Tavin.IventoryControl.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping()
    public ResponseEntity<UserResponseDto> RegisterUser(@RequestBody UserRequestDto userRequestDto) {
       return new ResponseEntity<>(userService.register(userRequestDto), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Void> UpdateUser(@RequestParam String id,
                                                        @RequestBody UserRequestDto userRequestDto){
        userService.updateUser(id, userRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
