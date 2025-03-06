package com.Tavin.IventoryControl.infra.mapper;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import com.Tavin.IventoryControl.domain.products.Product;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPostRequestDto;
import org.springframework.stereotype.Component;

@Component
public class InboundMapper {
    public static final InboundMapper INSTANCE = new InboundMapper();
        public Inbound toInbound(InboundPostRequestDto postRequestDto, Product product, User user) {
            Inbound inbound = new Inbound();
            inbound.setName(postRequestDto.name());
            inbound.setDescription(postRequestDto.desc());
            inbound.setQuantity(postRequestDto.quantity());
            inbound.setProduct(product);
            inbound.setUser(user);
            return inbound;
        }
}
