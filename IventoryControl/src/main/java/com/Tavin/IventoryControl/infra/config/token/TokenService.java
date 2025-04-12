package com.Tavin.IventoryControl.infra.config.token;

import com.Tavin.IventoryControl.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("InventoryControl")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generatedExpirationDate())
                    .sign(algorithm);

            return token;

        }catch (JWTCreationException e) {
          throw new JWTCreationException("error generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm).
                    withIssuer("InventoryControl").
                    build().
                    verify(token).
                    getSubject();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    private Instant generatedExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
