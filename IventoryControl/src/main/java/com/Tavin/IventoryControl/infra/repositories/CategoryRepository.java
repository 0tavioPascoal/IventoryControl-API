package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.products.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("select c from Category c where upper(c.name) like upper(:name) ")
    Page<Category> findByName(
            @Param("name") String name, Pageable pageable);
}
