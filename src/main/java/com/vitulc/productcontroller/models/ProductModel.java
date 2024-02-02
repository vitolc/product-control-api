package com.vitulc.productcontroller.models;

import com.vitulc.productcontroller.dtos.ProductRecordDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCT")
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotNull
    @Column
    private BigDecimal value;

    public ProductModel(ProductRecordDto productRecordDto) {
        this.name = productRecordDto.name();
        this.value = productRecordDto.value();
    }

    public ProductModel() {

    }

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
