package com.Tavin.IventoryControl.infra.dtos.inbound;

public record InboundPostRequestDto(
                                    Integer quantity,
                                    String idProduct,
                                    String idUser) {
}
