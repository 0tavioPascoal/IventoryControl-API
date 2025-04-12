package com.Tavin.IventoryControl.infra.dtos.outbound;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OutboundPostRequestDto(
        @NotBlank(message = "Required Location!")
        String location,
        @NotBlank(message = "Required Id for User!")
        String idUser,
        @NotBlank(message = "Required Id for Product!")
        String idProduct,
        @NotNull(message = "Required quantity!")
        Integer quantity) {
}
