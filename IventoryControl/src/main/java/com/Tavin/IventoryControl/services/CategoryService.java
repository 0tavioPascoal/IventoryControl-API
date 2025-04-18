package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.products.Category;
import com.Tavin.IventoryControl.infra.dtos.category.CategoryPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.category.CategoryRequestDto;
import com.Tavin.IventoryControl.infra.exceptions.BadRequestException;
import com.Tavin.IventoryControl.infra.exceptions.ResourceNotFoundException;
import com.Tavin.IventoryControl.infra.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category CreateCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.name());
        return categoryRepository.save(category);
    }

    public Category findByCategoryForId(String id){
        return categoryRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("Category not found"));
    }

    public Page<Category> findAllCategory(String name,Pageable pageable) {
       return categoryRepository.findByName("%" + name + "%",pageable);
    }

    public void DeleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(String id,CategoryPutRequestDto CategoryPutRequestDto) {
       Category category = findByCategoryForId(id);
       category.setName(CategoryPutRequestDto.name());
        return categoryRepository.save(category);
    }
}
