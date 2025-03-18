package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.dtos.user.UserPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.user.UserRequestDto;
import com.Tavin.IventoryControl.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User route")
public class UserController {

    private final UserService userService;

    @PutMapping()
    @Operation( summary = "Updated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated successfully")
    })
    public ResponseEntity<User> UpdateUser(@RequestParam String id,
                                           @RequestBody UserPutRequestDto userPutRequestDto) {
        return new ResponseEntity<>(userService.UpdateUser(id, userPutRequestDto), HttpStatus.OK);
    }

    @GetMapping("/findById")
    @Operation(summary = "Find by user for id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Searched user for id"),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    public ResponseEntity<User> GetUserForId(@RequestParam String id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }

    @GetMapping()
    @Operation(summary = "Searched user for parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Searched user for parameters")
    })
    public ResponseEntity<Page<User>> GetUserForUsername(@RequestParam(value = "username",defaultValue = "",required = false) String username, Pageable pageable){
        return new ResponseEntity<>(userService.getAllUsers(username, pageable), HttpStatus.FOUND);
    }

    @DeleteMapping()
    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successfully deleted")
    })
    public ResponseEntity<Void> DeleteUser(@RequestParam String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
