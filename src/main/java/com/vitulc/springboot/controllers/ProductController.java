package com.vitulc.springboot.controllers;

import com.vitulc.springboot.dtos.ProductRecordDto;
import com.vitulc.springboot.models.ProductModel;
import com.vitulc.springboot.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductRecordDto productRecordDto){
        return this.productService.createProduct(productRecordDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts (){
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById (@PathVariable UUID id){
       return this.productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> editProduct (@PathVariable UUID id, @RequestBody ProductRecordDto productRecordDto){
        return this.productService.editProduct(id, productRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct (@PathVariable UUID id) {
        return this.productService.deleteProduct(id);
    }

}
