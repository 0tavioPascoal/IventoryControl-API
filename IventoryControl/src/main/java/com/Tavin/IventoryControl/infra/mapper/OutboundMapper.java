package com.Tavin.IventoryControl.infra.mapper;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.domain.movimentations.Outbound;
import com.Tavin.IventoryControl.domain.movimentations.TypesMovimentations;
import com.Tavin.IventoryControl.domain.products.Product;
import org.springframework.stereotype.Component;

@Component
public class OutboundMapper {
    public static final OutboundMapper INSTANCE = new OutboundMapper();
        public Outbound toOutbound(Integer quantity, String location, Product product, User user) {
            Outbound outbound = new Outbound();
            outbound.setQuantity(quantity);
            outbound.setLocation(location);
            outbound.setTypesMovimentations(TypesMovimentations.WAITING);
            outbound.setProduct(product);
            outbound.setUser(user);
            return outbound;
        }
}
