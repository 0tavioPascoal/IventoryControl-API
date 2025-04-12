package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.inbound.InboundPutRequestDto;
import com.Tavin.IventoryControl.services.InboundService;
import com.Tavin.IventoryControl.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Inbound route")
public class InboundController {

    private final InboundService inboundService;

    @PostMapping()
    @Operation( summary = "Create Inbound")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "Created Inbound")
    })
    public ResponseEntity<Inbound> createInbound(@RequestBody @Valid InboundPostRequestDto requestDto) {
        return  new ResponseEntity<>(inboundService.createInbound(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @Operation(summary = "Get Inbound for Id")
    @ApiResponses(value = {
            @ApiResponse(  responseCode ="304", description = "Searched inbound for id"),
            @ApiResponse(responseCode = "400", description = "inbound not found")
    })
    public ResponseEntity<Inbound> getInboundById(@RequestParam("id") String id) {
        return new ResponseEntity<>(inboundService.getInboundById(id), HttpStatus.FOUND);
    }


    @PutMapping()
    @Operation(summary = "Updated Inbound")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully")
    })
    public ResponseEntity<Inbound> updateInboundStatus(@RequestParam String id ,@RequestBody  InboundPutRequestDto requestDto) {
        return new ResponseEntity<>(inboundService.updatedStatusInbound(requestDto, id), HttpStatus.OK);
    }

    @DeleteMapping()
    @Operation(  summary = "Delete Inbound for id")
    @ApiResponses(value = {
            @ApiResponse(  responseCode = "204", description = "Deleted successfully")
    })
    public ResponseEntity<Inbound> deleteInbound(@RequestParam String id) {
        inboundService.deleteInbound(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    @Operation(summary = "Get inbounds for parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Searched Inbounds for parameters")
    })
    public ResponseEntity<Page<Inbound>> getInbounds(@RequestParam(value = "productName", defaultValue = "", required = false) String productName,
                                                     @RequestParam(value = "userName", defaultValue = "", required = false) String userName,
                                                     Pageable pageable) {
        return new ResponseEntity<>(inboundService.getInbounds(pageable, productName, userName), HttpStatus.OK);
    }
}
