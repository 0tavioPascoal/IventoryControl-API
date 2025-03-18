package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.movimentations.Outbound;
import com.Tavin.IventoryControl.infra.dtos.outbound.OutboundPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.outbound.OutboundPutRequestDto;
import com.Tavin.IventoryControl.services.OutboundService;
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

@RestController
@RequestMapping("/outbound")
@RequiredArgsConstructor
@Tag( name = "Outbound route")
public class OutboundController {

    private final OutboundService outboundService;

    @PostMapping()
    @Operation(summary = "Create Outbound")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Outbound created")
    })
    public ResponseEntity<Outbound> createOutbound(@RequestBody @Valid OutboundPostRequestDto outboundPost) {
        return new ResponseEntity<>(outboundService.createOutbound(outboundPost), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @Operation(summary = "Search Outbound for id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Searched Outbound for id"),
            @ApiResponse( responseCode = "400", description = "Outbound not found")
    })
    public ResponseEntity<Outbound> findOutboundById(@RequestParam("id") String id) {
        return new ResponseEntity<>(outboundService.findById(id), HttpStatus.FOUND);
    }

    @PutMapping()
    @Operation(summary = "Updated Outbound")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200",  description = "Updated successfully")
    })
    public ResponseEntity<Outbound> updateOutbound(@RequestParam("id") String id,
                                                    @RequestBody OutboundPutRequestDto outboundPut) {
        return new ResponseEntity<>(outboundService.updateOutbound(id, outboundPut), HttpStatus.OK);
    }

    @DeleteMapping()
    @Operation(summary = "Delete Outbound")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted Outbound")
    })
    public ResponseEntity<Outbound> deleteOutbound(@RequestParam("id") String id) {
        outboundService.deleteOutboundForId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    @Operation(summary = "Searched Outbound for parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Searched Outbound for parameters")
    })
    public ResponseEntity<Page<Outbound>> getAllOutbounds(
            @RequestParam(value = "productName", defaultValue = "", required = false) String productName,
            @RequestParam(value = "userName", defaultValue = "", required = false) String userName,
            @RequestParam(value = "location", defaultValue = "", required = false) String location
            ,Pageable pageable) {
        return new ResponseEntity<>(outboundService.getOutbounds(productName, userName, location, pageable), HttpStatus.FOUND);
    }

}
