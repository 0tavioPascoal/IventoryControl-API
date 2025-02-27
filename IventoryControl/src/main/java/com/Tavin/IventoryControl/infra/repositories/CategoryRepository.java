package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.products.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
