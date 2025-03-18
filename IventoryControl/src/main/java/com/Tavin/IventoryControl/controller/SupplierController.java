package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.Supplier;
import com.Tavin.IventoryControl.infra.dtos.supplier.SupplierPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.supplier.SupplierRequestDto;
import com.Tavin.IventoryControl.services.SupplierService;
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
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Tag(name = "Supplier route")
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping()
    @Operation( summary = "Created Supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Supplier")
    })
    public ResponseEntity<Supplier> CreateSupplier(@RequestBody @Valid SupplierRequestDto requestDto){
        return new ResponseEntity<>(supplierService.CreateSupplier(requestDto) , HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @Operation( summary = "Searched for Supplier for Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Searched for id")
    })
    public ResponseEntity<Supplier> GetSupplierById(@RequestParam String id){
        return new ResponseEntity<>(supplierService.GetSupplierById(id), HttpStatus.FOUND);
    }

    @GetMapping()
    @Operation( summary = "Get Supplier for parameters")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "302", description = "Searched for Supplier for parameters")
    })
    public ResponseEntity<Page<Supplier>> GetSupplierByName(@RequestParam(value = "name", defaultValue =  "", required = false) String name,
                                                            Pageable pageable){
        return new ResponseEntity<>(supplierService.GetSupplierByName(name, pageable), HttpStatus.FOUND);
    }

    @PutMapping()
    @Operation(summary = "Updated Supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Successfully")
    })
    public ResponseEntity<Supplier> UpdateSupplier(@RequestParam String id,
                                                        @RequestBody SupplierPutRequestDto requestDto){
     return new ResponseEntity<>(supplierService.UpdateSupplier(id, requestDto) , HttpStatus.OK);
    }

    @DeleteMapping()
    @Operation( summary = "Deleted successfully")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully")
    })
    public ResponseEntity<Void> DeleteSupplier(@RequestParam String id){
        supplierService.DeleteSupplier(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
