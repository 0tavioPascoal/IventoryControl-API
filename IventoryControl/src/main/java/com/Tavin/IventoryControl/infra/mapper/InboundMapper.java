package com.Tavin.IventoryControl.infra.mapper;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import com.Tavin.IventoryControl.domain.movimentations.TypesMovimentations;
import com.Tavin.IventoryControl.domain.products.Product;
import org.springframework.stereotype.Component;

@Component
public class InboundMapper {
    public static final InboundMapper INSTANCE = new InboundMapper();
        public Inbound toInbound(Integer quantity, Product product, User user) {
            Inbound inbound = new Inbound();
            inbound.setQuantity(quantity);
            inbound.setProduct(product);
            inbound.setTypesMovimentations(TypesMovimentations.WAITING);
            inbound.setUser(user);
            return inbound;
        }
}
