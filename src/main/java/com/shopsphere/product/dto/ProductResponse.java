package com.shopsphere.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {

    private UUID id;

    private String sku;

    private String name;

    private String description;

    private BigDecimal price;

    private String category;

    private boolean active;
}
