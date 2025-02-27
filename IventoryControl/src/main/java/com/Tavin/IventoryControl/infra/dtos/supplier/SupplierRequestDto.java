package com.Tavin.IventoryControl.infra.dtos.supplier;

public record SupplierRequestDto(String name,
                                 String email,
                                 String phone,
                                 String cnpj) {
}
