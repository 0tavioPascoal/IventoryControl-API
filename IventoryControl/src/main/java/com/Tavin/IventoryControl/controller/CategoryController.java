package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.products.Category;
import com.Tavin.IventoryControl.infra.dtos.category.CategoryPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.category.CategoryRequestDto;
import com.Tavin.IventoryControl.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> CreateCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.CreateCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Category> GetCategoryForId(@RequestParam String id) {
        return new ResponseEntity<>(categoryService.findByCategoryForId(id), HttpStatus.FOUND);
    }

    @GetMapping("/findByName")
    public ResponseEntity<Page<Category>> GetAllCategory(@RequestParam(value = "name",defaultValue = "",required = false) String name,
                                                            Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAllCategory(name,pageable), HttpStatus.FOUND);
    }

    @DeleteMapping()
    public ResponseEntity<Void> DeleteCategory(@RequestParam String id) {
        categoryService.DeleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Category> UpdateCategory(@RequestParam String id ,@RequestBody CategoryPutRequestDto categoryPutRequestDto) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryPutRequestDto), HttpStatus.OK);
    }

}
