package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPutRequestDto;
import com.Tavin.IventoryControl.services.InboundService;
import com.Tavin.IventoryControl.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inbound")
@RequiredArgsConstructor
public class InboundController {

    private final InboundService inboundService;

    @PostMapping()
    public ResponseEntity<Inbound> createInbound(@RequestBody InboundPostRequestDto requestDto) {
        return  new ResponseEntity<>(inboundService.createInbound(requestDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Inbound> getInboundById(@RequestParam("id") String id) {
        return new ResponseEntity<>(inboundService.getInboundById(id), HttpStatus.FOUND);
    }


    @PutMapping()
    public ResponseEntity<Inbound> updateInboundStatus(@RequestParam String id ,@RequestBody InboundPutRequestDto requestDto) {
        return new ResponseEntity<>(inboundService.updatedStatusInbound(requestDto, id), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Inbound> deleteInbound(@RequestParam String id) {
        inboundService.deleteInbound(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Inbound>> getInbounds(@RequestParam(value = "productName", defaultValue = "", required = false) String productName,
                                                     @RequestParam(value = "userName", defaultValue = "", required = false) String userName,
                                                     Pageable pageable) {
        return new ResponseEntity<>(inboundService.getInbounds(pageable, productName, userName), HttpStatus.OK);
    }
}
