package com.Tavin.IventoryControl.infra.dtos.supplier;

import java.util.UUID;

public record SupplierResponseDto(UUID id,
                                  String name,
                                  String phone,
                                  String cnpj,
                                  String email) {
}
