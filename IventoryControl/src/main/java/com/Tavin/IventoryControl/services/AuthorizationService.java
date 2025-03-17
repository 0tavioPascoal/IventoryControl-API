package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.infra.config.token.TokenService;
import com.Tavin.IventoryControl.infra.dtos.auth.login.LoginDto;
import com.Tavin.IventoryControl.infra.dtos.auth.login.ResponseLoginDto;
import com.Tavin.IventoryControl.infra.dtos.auth.register.RegisterRequestDto;
import com.Tavin.IventoryControl.infra.dtos.auth.register.RegisterResponseDto;
import com.Tavin.IventoryControl.infra.exceptions.BadRequestException;
import com.Tavin.IventoryControl.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public ResponseLoginDto login(LoginDto data) {
        User user = userRepository.findByEmail(data.email()).orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
        if (passwordEncoder.matches(data.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return new ResponseLoginDto(user.getUsername(), token);
        }
        throw new BadRequestException("Wrong password!");
    }

    public RegisterResponseDto register(RegisterRequestDto data) {
        Optional<User> user = userRepository.findByEmail(data.email());

        if(user.isEmpty()){
            User newUser = new User();
            newUser.setUsername(data.username());
            newUser.setPassword(passwordEncoder.encode(data.password()));
            newUser.setEmail(data.email());
            userRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return new RegisterResponseDto(newUser.getUsername(), newUser.getId() ,token);
        }
        throw new BadRequestException("Register error!");
    }
}
