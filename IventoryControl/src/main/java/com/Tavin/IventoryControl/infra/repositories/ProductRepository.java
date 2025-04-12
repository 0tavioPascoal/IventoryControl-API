package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("select p from Product p where upper(p.name) like upper(:name) and upper (p.category.name) like upper(:category) ")
    Page<Product> findByCategoryAndName(@Param("category") String category, @Param("name") String name, Pageable pageable);
}
