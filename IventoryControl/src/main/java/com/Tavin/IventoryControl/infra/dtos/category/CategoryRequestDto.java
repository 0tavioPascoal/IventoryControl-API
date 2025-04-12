package com.Tavin.IventoryControl.infra.dtos.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank(message = "Required field!")
        String name) {
}
