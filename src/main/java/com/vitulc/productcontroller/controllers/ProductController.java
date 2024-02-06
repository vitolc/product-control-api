package com.vitulc.productcontroller.controllers;

import com.vitulc.productcontroller.dtos.ProductResponseRecordDto;
import com.vitulc.productcontroller.dtos.ProductRecordDto;
import com.vitulc.productcontroller.services.ProductService;
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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> createProduct(@RequestBody ProductRecordDto productRecordDto){
        return this.productService.createProduct(productRecordDto);
    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<ProductResponseRecordDto>> getAllProducts (){
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ProductResponseRecordDto> getProductById (@PathVariable UUID id){
       return this.productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> editProduct (@PathVariable UUID id, @RequestBody ProductRecordDto productRecordDto){
        return this.productService.editProduct(id, productRecordDto);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> deleteProduct (@PathVariable UUID id) {
        return this.productService.deleteProduct(id);
    }

}
