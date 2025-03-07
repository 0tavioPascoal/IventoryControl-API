package com.Tavin.IventoryControl.infra.dtos.outbound;

import com.Tavin.IventoryControl.domain.movimentations.TypesMovimentations;

public record OutboundPutRequestDto(TypesMovimentations types, String Location) {
}
