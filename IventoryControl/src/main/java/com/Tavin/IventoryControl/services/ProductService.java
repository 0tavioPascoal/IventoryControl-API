package com.Tavin.IventoryControl.services;

import com.Tavin.IventoryControl.domain.products.Product;
import com.Tavin.IventoryControl.infra.dtos.Product.ProductPostRequestDto;
import com.Tavin.IventoryControl.infra.dtos.Product.ProductPutRequestDto;
import com.Tavin.IventoryControl.infra.mapper.ProductMapper;
import com.Tavin.IventoryControl.infra.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierService supplierService;
    private final CategoryService categoryService;



    public Product CreateProduct(ProductPostRequestDto productPostRequestDto) {
        var category = categoryService.findByCategoryForId(productPostRequestDto.idCategory());
        var supplier = supplierService.GetSupplierById(productPostRequestDto.idSupplier());

      return productRepository.save(ProductMapper.INSTANCE.toProduct(productPostRequestDto, supplier, category));
    }

    public Product getProductById(String id) {
        UUID idProduct = UUID.fromString(id);
        return productRepository.findById(idProduct).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Page<Product> getAllProducts(String category,String name,Pageable pageable) {
        return productRepository.findByCategoryAndName("%" + category + "%","%" + name + "%",pageable);
    }

    public void deleteProductById(String id) {
        var idProduct = getProductById(id);
        productRepository.deleteById(idProduct.getId());
    }

    public Product updateProduct(String id, ProductPutRequestDto productPutRequestDto) {
        var supplier = supplierService.GetSupplierById(productPutRequestDto.idSupplier());
        var category = categoryService.findByCategoryForId(productPutRequestDto.idCategory());

        getProductById(id);
        Product product = getProductById(id);
        product.setName(productPutRequestDto.name());
        product.setDescription(productPutRequestDto.desc());
        product.setType(productPutRequestDto.type());
        product.setQuantity(productPutRequestDto.quantity());
        product.setPriceUni(productPutRequestDto.priceUni());
        product.setCategory(category);
        product.setSupplier(supplier);

        return productRepository.save(product);
    }

}
