package com.Tavin.IventoryControl.infra.dtos.inbound;

import com.Tavin.IventoryControl.domain.movimentations.TypesMovimentations;

public record InboundPutRequestDto(TypesMovimentations types) {
}
