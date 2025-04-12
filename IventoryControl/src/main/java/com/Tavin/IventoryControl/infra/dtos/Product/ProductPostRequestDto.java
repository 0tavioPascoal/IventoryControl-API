package com.Tavin.IventoryControl.infra.dtos.Product;

import com.Tavin.IventoryControl.domain.products.TypesProducts;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductPostRequestDto(
        @NotBlank(message = "Required Field!")
        String name,
        String desc,
        @NotNull(message = "Required Quantity!")
        Integer quantity,
        @NotNull(message = "Required unity price!")
        Double priceUni,
        @NotNull(message = "Required Type for Product!")
        TypesProducts type,
        @NotBlank(message = "Required Id for category!")
        String idCategory,
        @NotBlank(message = "Required Id for Supplier!")
        String idSupplier) {
}
