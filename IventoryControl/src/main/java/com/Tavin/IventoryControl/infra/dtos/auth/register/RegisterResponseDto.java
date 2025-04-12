package com.Tavin.IventoryControl.infra.dtos.auth.register;

import java.util.UUID;

public record RegisterResponseDto(String username,String userId, String token) {
}
