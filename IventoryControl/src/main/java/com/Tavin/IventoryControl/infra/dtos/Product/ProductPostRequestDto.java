package com.Tavin.IventoryControl.infra.dtos.Product;

import com.Tavin.IventoryControl.domain.products.TypesProducts;

public record ProductPostRequestDto(String name,
                                    String desc,
                                    Integer quantity,
                                    Double priceUni,
                                    TypesProducts type,
                                    String idCategory,
                                    String idSupplier) {
}
