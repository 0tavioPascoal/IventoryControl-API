package com.Tavin.IventoryControl.infra.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "Required filed!")
        String username,
        @Email
                @NotBlank(message = "Required email!")
        String email,
        @Size(min = 5)
        @NotBlank(message = "Required Field!")
        String password) {
}
