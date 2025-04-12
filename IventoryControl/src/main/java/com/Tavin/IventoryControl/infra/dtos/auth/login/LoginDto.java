package com.Tavin.IventoryControl.infra.dtos.auth.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @Email
        @NotBlank(message = "Required field!")
        String email,
        @NotBlank(message = "Required field!")
        @Size(min = 5, max = 20)
        String password) {
}
