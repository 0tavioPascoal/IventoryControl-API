package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPostRequestDto;
import com.Tavin.IventoryControl.services.InboundService;
import com.Tavin.IventoryControl.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inbound")
@RequiredArgsConstructor
public class InboundController {

    private final InboundService inboundService;

    @PostMapping()
    public ResponseEntity<Inbound> createInbound(@RequestBody InboundPostRequestDto requestDto) {
        return  new ResponseEntity<>(inboundService.createInbound(requestDto), HttpStatus.CREATED);
    }

}
