package com.Tavin.IventoryControl.infra.dtos.outbound;

import com.Tavin.IventoryControl.domain.movimentations.TypesMovimentations;

public record OutboundPostRequestDto(String location, String idUser, String idProduct, Integer quantity) {
}
