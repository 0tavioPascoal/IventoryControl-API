package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.products.Category;
import com.Tavin.IventoryControl.infra.dtos.category.CategoryPutRequestDto;
import com.Tavin.IventoryControl.infra.dtos.category.CategoryRequestDto;
import com.Tavin.IventoryControl.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Category route")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    @Operation(summary = "Create Category", description = "create new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "category created successfully")
    })
    public ResponseEntity<Category> CreateCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.CreateCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("findById")
    @Operation(summary = "Searched for Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "category found by id")
    })
    public ResponseEntity<Category> GetCategoryForId(@RequestParam String id) {
        return new ResponseEntity<>(categoryService.findByCategoryForId(id), HttpStatus.FOUND);
    }

    @GetMapping("")
    @Operation(summary = "Find alls for parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "category found by parameters")
    })
    public ResponseEntity<Page<Category>> GetAllCategory(@RequestParam(value = "name",defaultValue = "",required = false) String name,
                                                            Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAllCategory(name,pageable), HttpStatus.FOUND);
    }

    @DeleteMapping()
    @Operation(summary = "Delete category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully")
    })
    public ResponseEntity<Void> DeleteCategory(@RequestParam String id) {
        categoryService.DeleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    @Operation(summary = "Updated category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated successfully")
    })
    public ResponseEntity<Category> UpdateCategory(@RequestParam String id ,@RequestBody CategoryPutRequestDto categoryPutRequestDto) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryPutRequestDto), HttpStatus.OK);
    }

}
