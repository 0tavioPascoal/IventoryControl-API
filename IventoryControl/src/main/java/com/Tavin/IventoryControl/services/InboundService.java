package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPostRequestDto;
import com.Tavin.IventoryControl.infra.mapper.InboundMapper;
import com.Tavin.IventoryControl.infra.repositories.InboundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InboundService {

    private final UserService userService;
    private final ProductService productService;
    private final InboundRepository inboundRepository;

    public Inbound createInbound(InboundPostRequestDto requestDto) {
        var product = productService.getProductById(requestDto.idProduct());
        var user = userService.getUserById(requestDto.idUser());

        return inboundRepository.save(InboundMapper.INSTANCE.toInbound(requestDto, product, user));
    }
}
