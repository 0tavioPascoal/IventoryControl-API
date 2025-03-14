package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.Supplier;
import com.Tavin.IventoryControl.infra.dtos.supplier.SupplierPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.supplier.SupplierRequestDto;
import com.Tavin.IventoryControl.infra.exceptions.ResourceNotFoundException;
import com.Tavin.IventoryControl.infra.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier CreateSupplier(SupplierRequestDto requestDto){
        Supplier supplier = new Supplier();
        supplier.setName(requestDto.name());
        supplier.setCnpj(requestDto.cnpj());
        supplier.setEmail(requestDto.email());
        supplier.setPhone(requestDto.phone());

        return supplierRepository.save(supplier);
    }

    public Supplier GetSupplierById(String id){
        UUID uuid = UUID.fromString(id);
        return supplierRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    public Page<Supplier> GetSupplierByName(String name, Pageable pageable){
        return supplierRepository.findByName("%" + name + "%", pageable);
    }

    public Supplier UpdateSupplier(String id, SupplierPutRequestDto requestDto){

        Supplier supplier = GetSupplierById(id);
        supplier.setName(requestDto.name());
        supplier.setCnpj(requestDto.cnpj());
        supplier.setPhone(requestDto.phone());
        supplier.setEmail(requestDto.email());
        return supplierRepository.save(supplier);
    }

    public void DeleteSupplier(String id){
        Supplier supplier = GetSupplierById(id);
        supplierRepository.delete(supplier);
    }

}
