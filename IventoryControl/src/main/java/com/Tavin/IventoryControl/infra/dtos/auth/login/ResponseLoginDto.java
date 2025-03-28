package com.Tavin.IventoryControl.infra.dtos.auth.login;

import com.Tavin.IventoryControl.domain.User;

public record ResponseLoginDto( String token, String id) {
}
