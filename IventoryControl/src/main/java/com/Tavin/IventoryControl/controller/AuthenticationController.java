package com.Tavin.IventoryControl.controller;


import com.Tavin.IventoryControl.infra.dtos.auth.login.LoginDto;
import com.Tavin.IventoryControl.infra.dtos.auth.login.ResponseLoginDto;
import com.Tavin.IventoryControl.infra.dtos.auth.register.RegisterRequestDto;
import com.Tavin.IventoryControl.infra.dtos.auth.register.RegisterResponseDto;
import com.Tavin.IventoryControl.services.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login (@RequestBody @Valid LoginDto data) {
       return new ResponseEntity<>(authorizationService.login(data), HttpStatus.OK);
    }

    @PostMapping("/register")
    public  ResponseEntity<RegisterResponseDto> register (@RequestBody @Valid RegisterRequestDto data){
      return new ResponseEntity<>(authorizationService.register(data), HttpStatus.OK);
    }
}
