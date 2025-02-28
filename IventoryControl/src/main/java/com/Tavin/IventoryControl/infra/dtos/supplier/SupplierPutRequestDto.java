package com.Tavin.IventoryControl.infra.dtos.supplier;

public record SupplierPutRequestDto(String id,
                                    String name,
                                    String email,
                                    String cnpj,
                                    String phone) {
}
