package com.Tavin.IventoryControl.infra.dtos.auth.login;

import com.Tavin.IventoryControl.domain.User;

public record ResponseLoginDto(User user, String token) {
}
