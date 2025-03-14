package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.Supplier;
import com.Tavin.IventoryControl.infra.dtos.supplier.SupplierPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.supplier.SupplierRequestDto;
import com.Tavin.IventoryControl.services.SupplierService;
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
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping()
    public ResponseEntity<Supplier> CreateSupplier(@RequestBody @Valid SupplierRequestDto requestDto){
        return new ResponseEntity<>(supplierService.CreateSupplier(requestDto) , HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Supplier> GetSupplierById(@RequestParam String id){
        return new ResponseEntity<>(supplierService.GetSupplierById(id), HttpStatus.FOUND);
    }

    @GetMapping("/findByName")
    public ResponseEntity<Page<Supplier>> GetSupplierByName(@RequestParam(value = "name", defaultValue =  "", required = false) String name,
                                                            Pageable pageable){
        return new ResponseEntity<>(supplierService.GetSupplierByName(name, pageable), HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<Supplier> UpdateSupplier(@RequestParam String id,
                                                        @RequestBody SupplierPutRequestDto requestDto){
     return new ResponseEntity<>(supplierService.UpdateSupplier(id, requestDto) , HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> DeleteSupplier(@RequestParam String id){
        supplierService.DeleteSupplier(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
