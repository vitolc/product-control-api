package com.vitulc.productcontroller.services;

import com.vitulc.productcontroller.dtos.ProductResponseRecordDto;
import com.vitulc.productcontroller.dtos.ProductRecordDto;
import com.vitulc.productcontroller.models.ProductModel;
import com.vitulc.productcontroller.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseEntity<String> createProduct(ProductRecordDto productRecordDto) {
      ProductModel productModel = new ProductModel(productRecordDto);
      productRepository.save(productModel);
      return ResponseEntity.status(HttpStatus.CREATED).body("PRODUCT CREATED SUCCESSFULLY");
    }

    public ResponseEntity<List<ProductResponseRecordDto>> getAllProducts() {
        List<ProductResponseRecordDto> productsList = productRepository.findAll().stream().map(ProductResponseRecordDto::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    public ResponseEntity<ProductResponseRecordDto> getProductById(UUID id){
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));
        ProductResponseRecordDto productResponseRecordDto = new ProductResponseRecordDto(productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseRecordDto);
    }

    @Transactional
    public ResponseEntity<String> editProduct(UUID id, ProductRecordDto productRecordDto){
        var productModel = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));
        productModel.setName(productRecordDto.name());
        productModel.setValue(productRecordDto.value());
        this.productRepository.save(productModel);
        return ResponseEntity.status(HttpStatus.OK).body("PRODUCT UPDATE SUCCESSFULLY");
    }

    @Transactional
    public ResponseEntity<String> deleteProduct(UUID id){
        Optional<ProductModel> deletedProduct = productRepository.findById(id);
        if (deletedProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PRODUCT NOT FOUND");
        }
        productRepository.delete(deletedProduct.get());
        return ResponseEntity.status(HttpStatus.OK).body("PRODUCT DELETED SUCCESSFULLY");
    }
}
