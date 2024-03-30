package com.palo.backend.controller;

import com.palo.backend.domain.Product;
import com.palo.backend.dto.ProductDTO;
import com.palo.backend.service.ConversionService;
import com.palo.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductDTO> getAllProducts(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return productService.findAll(pageNumber, pageSize, sortBy).stream()
                .map(ConversionService::toProductDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("{id}")
    public ProductDTO getProductById(@PathVariable Integer id) {

        Product byId = productService.findById(id);
        return ConversionService.toProductDTO(byId);
    }


    @PostMapping("")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {

        Product productToProcess = ConversionService.toProduct(productDTO);
        Product save = productService.save(productToProcess);

        return ConversionService.toProductDTO(save);

    }

    @PutMapping("{id}")
    public ProductDTO updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {

        Product product = ConversionService.toProduct(productDTO);
        Product save = productService.update(id, product);

        return ConversionService.toProductDTO(save);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
    }
}
