package com.vitulc.productcontroller.dtos;

import com.vitulc.productcontroller.models.ProductModel;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseRecordDto(String name, BigDecimal value, UUID id) {

    public ProductResponseRecordDto(ProductModel productModel){
        this(productModel.getName(), productModel.getValue(), productModel.getIdProduct());
    }
}
