package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.movimentations.Outbound;
import com.Tavin.IventoryControl.infra.dtos.outbound.OutboundPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.outbound.OutboundPutRequestDto;
import com.Tavin.IventoryControl.services.OutboundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outbound")
@RequiredArgsConstructor
public class OutboundController {

    private final OutboundService outboundService;

    @PostMapping()
    public ResponseEntity<Outbound> createOutbound(@RequestBody @Valid OutboundPostRequestDto outboundPost) {
        return new ResponseEntity<>(outboundService.createOutbound(outboundPost), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    public ResponseEntity<Outbound> findOutboundById(@RequestParam("id") String id) {
        return new ResponseEntity<>(outboundService.findById(id), HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<Outbound> updateOutbound(@RequestParam("id") String id,
                                                    @RequestBody OutboundPutRequestDto outboundPut) {
        return new ResponseEntity<>(outboundService.updateOutbound(id, outboundPut), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Outbound> deleteOutbound(@RequestParam("id") String id) {
        outboundService.deleteOutboundForId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Outbound>> getAllOutbounds(
            @RequestParam(value = "productName", defaultValue = "", required = false) String productName,
            @RequestParam(value = "userName", defaultValue = "", required = false) String userName,
            @RequestParam(value = "location", defaultValue = "", required = false) String location
            ,Pageable pageable) {
        return new ResponseEntity<>(outboundService.getOutbounds(productName, userName, location, pageable), HttpStatus.FOUND);
    }

}
