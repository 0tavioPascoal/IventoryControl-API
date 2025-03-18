package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.products.Product;
import com.Tavin.IventoryControl.infra.dtos.Product.ProductPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.Product.ProductPutRequestDto;
import com.Tavin.IventoryControl.services.ProductService;
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
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product route")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    @Operation(summary = "Created Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Product")
    })
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductPostRequestDto productPostRequestDto) {
        return new ResponseEntity<>(productService.CreateProduct(productPostRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @Operation(summary = "Find by Product for id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Searched Product for id")
    })
    public ResponseEntity<Product> getProductForId(@RequestParam("id") String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.FOUND);
    }

    @GetMapping()
    @Operation(summary = "Get Products for parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Searched product for parameters")
    })
    public ResponseEntity<Page<Product>> getProductsByCategoryAndName(@RequestParam(value = "category", required = false, defaultValue = "") String category,
                                                                      @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                      Pageable pageable) {
        return new ResponseEntity<>(productService.getAllProducts(category,name,pageable), HttpStatus.FOUND);
    }

    @DeleteMapping()
    @Operation(summary = "Delete product for id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted Product for id")
    })
    public ResponseEntity<Void> deleteProduct(@RequestParam("id") String id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    @Operation(summary = "Updated Product")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Updated Product")
    })
    public ResponseEntity<Product> updateProduct(@RequestParam String id, @RequestBody ProductPutRequestDto productPutRequestDto) {
        return new ResponseEntity<>(productService.updateProduct(id, productPutRequestDto), HttpStatus.OK);
    }
}
