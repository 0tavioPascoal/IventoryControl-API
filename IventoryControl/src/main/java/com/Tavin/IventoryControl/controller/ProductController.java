package com.Tavin.IventoryControl.controller;

import com.Tavin.IventoryControl.domain.products.Product;
import com.Tavin.IventoryControl.infra.dtos.Product.ProductPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.Product.ProductPutRequestDto;
import com.Tavin.IventoryControl.services.ProductService;
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
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductPostRequestDto productPostRequestDto) {
        return new ResponseEntity<>(productService.CreateProduct(productPostRequestDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Product> getProductForId(@RequestParam("id") String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.FOUND);
    }

    @GetMapping("/findByCategoryAndName")
    public ResponseEntity<Page<Product>> getProductsByCategoryAndName(@RequestParam(value = "category", required = false, defaultValue = "") String category,
                                                                      @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                      Pageable pageable) {
        return new ResponseEntity<>(productService.getAllProducts(category,name,pageable), HttpStatus.FOUND);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProduct(@RequestParam("id") String id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Product> updateProduct(@RequestParam String id, @RequestBody ProductPutRequestDto productPutRequestDto) {
        return new ResponseEntity<>(productService.updateProduct(id, productPutRequestDto), HttpStatus.OK);
    }
}
