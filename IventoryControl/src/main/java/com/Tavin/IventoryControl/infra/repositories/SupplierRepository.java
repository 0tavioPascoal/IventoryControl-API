package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {

    @Query("select s from Supplier s where upper(s.name) like upper(:name) ")
    Page<Supplier> findByName(
            @Param("name")String name, Pageable pageable);

    @Query("select s from Supplier s where (s.cnpj) like upper(:cnpj)")
    Page<Supplier> findByCnpj(
            @Param("cnpj") String cnpj, Pageable pageable);
}
