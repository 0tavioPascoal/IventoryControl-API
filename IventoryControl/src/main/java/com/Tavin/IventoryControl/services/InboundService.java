package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import com.Tavin.IventoryControl.domain.movimentations.TypesMovimentations;
import com.Tavin.IventoryControl.domain.products.Product;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPutRequestDto;
import com.Tavin.IventoryControl.infra.exceptions.BadRequestException;
import com.Tavin.IventoryControl.infra.exceptions.ResourceNotFoundException;
import com.Tavin.IventoryControl.infra.mapper.InboundMapper;
import com.Tavin.IventoryControl.infra.repositories.InboundRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InboundService {

    private final UserService userService;
    private final ProductService productService;
    private final InboundRepository inboundRepository;

    @Transactional
    public Inbound createInbound(InboundPostRequestDto requestDto) {
        var product = productService.getProductById(requestDto.idProduct());
        var user = userService.getUserById(requestDto.idUser());

        return inboundRepository.save(InboundMapper.INSTANCE.toInbound(requestDto.quantity(), product, user));
    }

    public Inbound getInboundById(String id) {
        UUID inboundId = UUID.fromString(id);
        return  inboundRepository.findById(inboundId).orElseThrow(() -> new ResourceNotFoundException("inbound not found"));
    }

    public void deleteInbound(String id) {
        var inboundId = getInboundById(id);
        Product product = inboundId.getProduct();

        product.setQuantity(product.getQuantity() - inboundId.getQuantity());

        inboundRepository.deleteById(inboundId.getId());
    }

    public Inbound updatedStatusInbound(InboundPutRequestDto requestDto, String id ) {
       var inbound = getInboundById(id);
       var product = inbound.getProduct();

       if(inbound.getTypesMovimentations() == requestDto.types()) {
           throw new BadRequestException("types cannot be updated");
       }

       if (inbound.getTypesMovimentations() == TypesMovimentations.COMPLETED){
           throw new BadRequestException("types cannot be updated");
       }

       inbound.setTypesMovimentations(requestDto.types());
       product.setQuantity(product.getQuantity() + inbound.getQuantity());

       return inboundRepository.save(inbound);
    }

    public Page<Inbound> getInbounds(Pageable pageable, String productName, String userName) {
        return inboundRepository.findByProductName("%" + productName + "%", "%" + userName + "%" ,pageable);
    }
}
