package com.Tavin.IventoryControl.infra.mapper;

import com.Tavin.IventoryControl.domain.Supplier;
import com.Tavin.IventoryControl.domain.products.Category;
import com.Tavin.IventoryControl.domain.products.Product;
import com.Tavin.IventoryControl.infra.dtos.Product.ProductPostRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static final ProductMapper INSTANCE = new ProductMapper();
    public Product toProduct(ProductPostRequestDto productPostRequestDto, Supplier supplier, Category category) {
        Product product = new Product();
        product.setDescription(productPostRequestDto.desc());
        product.setName(productPostRequestDto.name());
        product.setQuantity(productPostRequestDto.quantity());
        product.setType(productPostRequestDto.type());
        product.setPriceUni(productPostRequestDto.priceUni());

        product.setCategory(category);
        product.setSupplier(supplier);
        return product;
    }
}
