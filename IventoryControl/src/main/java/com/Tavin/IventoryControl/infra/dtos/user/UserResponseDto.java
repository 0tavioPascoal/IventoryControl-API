package com.Tavin.IventoryControl.infra.dtos.user;

import java.util.Date;
import java.util.UUID;

public record UserResponseDto(UUID id,
                              String username,
                              String email,
                              Date createdAt,
                              Date updatedAt) {
}
