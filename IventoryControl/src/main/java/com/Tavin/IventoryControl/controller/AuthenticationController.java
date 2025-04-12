package com.Tavin.IventoryControl.controller;


import com.Tavin.IventoryControl.infra.config.security.SecurityConfiguration;
import com.Tavin.IventoryControl.infra.dtos.auth.login.LoginDto;
import com.Tavin.IventoryControl.infra.dtos.auth.login.ResponseLoginDto;
import com.Tavin.IventoryControl.infra.dtos.auth.register.RegisterRequestDto;
import com.Tavin.IventoryControl.infra.dtos.auth.register.RegisterResponseDto;
import com.Tavin.IventoryControl.services.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication route")
public class AuthenticationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    @Operation(summary = "login", description = "log in")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Logged"),
        @ApiResponse(responseCode = "400", description = "Wrong password!"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    public ResponseEntity<ResponseLoginDto> login (@RequestBody @Valid LoginDto data) {
       return new ResponseEntity<>(authorizationService.login(data), HttpStatus.OK);
    }

    @PostMapping("/register")
    @Operation(summary = "register", description = "register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user created successfully"),
            @ApiResponse(responseCode = "400", description = "Register error!"),
            @ApiResponse(responseCode = "500", description = "Internal error!")
    })
    public  ResponseEntity<RegisterResponseDto> register (@RequestBody @Valid RegisterRequestDto data){
      return new ResponseEntity<>(authorizationService.register(data), HttpStatus.OK);
    }
}
