package com.palo.backend.service;

import com.palo.backend.domain.Product;
import com.palo.backend.dto.ProductDTO;

public class ConversionService {

    public static ProductDTO toProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(product.getImage());
        return productDTO;
    }

    public static Product toProduct(ProductDTO product) {
        Product productEntity = new Product();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setImage(product.getImage());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }
}
