package com.Tavin.IventoryControl.infra.dtos.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SupplierRequestDto(
        @NotBlank(message = "Required field!")
        String name,
        @Email
                @NotBlank(message = "Required field!")
        String email,

        String phone,
        @NotBlank(message = "Required CNPJ!")
        String cnpj) {
}
