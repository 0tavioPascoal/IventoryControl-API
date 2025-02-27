package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import com.Tavin.IventoryControl.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> RegisterUser(@RequestBody UserRequestDto userRequestDto) {
       return new ResponseEntity<>(userService.register(userRequestDto), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<User> UpdateUser(@RequestParam String id,
                                           @RequestBody UserPutRequestDto userPutRequestDto) {
        return new ResponseEntity<>(userService.UpdateUser(id, userPutRequestDto), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<User> GetUserForId(@RequestParam String id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Page<User>> GetUserForUsername(@RequestParam(value = "username",defaultValue = "",required = false) String username, Pageable pageable){
        return new ResponseEntity<>(userService.getAllUsers(username, pageable), HttpStatus.FOUND);
    }

    @DeleteMapping()
    public ResponseEntity<Void> DeleteUser(@RequestParam String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
