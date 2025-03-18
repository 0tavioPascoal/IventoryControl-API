package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.User;
import com.Tavin.IventoryControl.domain.movimentations.Outbound;
import com.Tavin.IventoryControl.domain.movimentations.TypesMovimentations;
import com.Tavin.IventoryControl.domain.products.Product;
import com.Tavin.IventoryControl.infra.dtos.outbound.OutboundPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.outbound.OutboundPutRequestDto;
import com.Tavin.IventoryControl.infra.exceptions.BadRequestException;
import com.Tavin.IventoryControl.infra.exceptions.ResourceNotFoundException;
import com.Tavin.IventoryControl.infra.mapper.OutboundMapper;
import com.Tavin.IventoryControl.infra.repositories.OutboundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboundService {

    private final OutboundRepository outboundRepository;
    private final ProductService productService;
    private final UserService userService;

    public Outbound createOutbound(OutboundPostRequestDto outbound) {
        Product product = productService.getProductById(outbound.idProduct());
        User user = userService.getUserById(outbound.idUser());
        return outboundRepository.save(OutboundMapper.INSTANCE.toOutbound(outbound.quantity(), outbound.location(), product, user));
    }

    public Outbound findById(String id) {
        UUID idOutbound = UUID.fromString(id);
        return outboundRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Outbound not found"));
    }

    public Outbound updateOutbound(String id, OutboundPutRequestDto outboundPut) {
       Outbound outbound =  findById(id);
       Product product = outbound.getProduct();

       if(outbound.getTypesMovimentations() == TypesMovimentations.COMPLETED){
           throw  new BadRequestException("Outbound already exists");
       }

       if(outbound.getTypesMovimentations() == outboundPut.types()){
           throw  new BadRequestException("Outbound already exists");
       }

       if(outboundPut.Location() != null){
           outbound.setLocation(outboundPut.Location());
       }

       outbound.setTypesMovimentations(outboundPut.types());
       product.setQuantity(product.getQuantity() - outbound.getQuantity());

       return outboundRepository.save(outbound);
    }

    public void deleteOutboundForId(String id) {
        var idOutbound = findById(id);
        outboundRepository.deleteById(idOutbound.getId());
    }

    public Page<Outbound> getOutbounds(String productName ,String userName,String location,Pageable pageable) {
        return outboundRepository.findByLocationAndUserNameAndProductName(
                "%"+productName+"%", "%" + userName + "%", "%" + location + "%", pageable);
    }
}
