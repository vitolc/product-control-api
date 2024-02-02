package com.vitulc.productcontroller.services;

import com.vitulc.productcontroller.controllers.ProductController;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseEntity<ProductModel> createProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel(productRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(this.productRepository.save(productModel));
    }

    public ResponseEntity<List<ProductModel>> getAllProducts() {

        List<ProductModel> productsList = this.productRepository.findAll();

        if(!productsList.isEmpty()){
            for (ProductModel product: productsList){
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @Transactional
    public ResponseEntity<ProductModel> getProductById(UUID id){
        var productModel = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));
        productModel.add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(productModel);
    }

    @Transactional
    public ResponseEntity<ProductModel> editProduct(UUID id, ProductRecordDto productRecordDto){
        var productModel = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));
        productModel.setName(productRecordDto.name());
        productModel.setValue(productRecordDto.value());
        this.productRepository.save(productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productModel);
    }

    public ResponseEntity<Object> deleteProduct(UUID id){
        Optional<ProductModel> deletedProduct = productRepository.findById(id);
        if (deletedProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PRODUCT NOT FOUND");
        }
        productRepository.delete(deletedProduct.get());
        return ResponseEntity.status(HttpStatus.OK).body("PRODUCT DELETED SUCCESSFULY");
    }
}
