package com.Tavin.IventoryControl.infra.dtos.inbound;

public record InboundPostRequestDto(String name,
                                    String desc,
                                    Integer quantity,
                                    String idProduct,
                                    String idUser) {
}
