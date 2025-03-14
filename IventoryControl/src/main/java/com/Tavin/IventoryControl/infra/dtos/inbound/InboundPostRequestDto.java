package com.Tavin.IventoryControl.infra.dtos.inbound;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InboundPostRequestDto(
        @NotNull(message = "Required quantity!")
        Integer quantity,
        @NotBlank(message = "Required Id for Product!")
        String idProduct,
        @NotBlank(message = "Requires Id for User!")
        String idUser) {
}
