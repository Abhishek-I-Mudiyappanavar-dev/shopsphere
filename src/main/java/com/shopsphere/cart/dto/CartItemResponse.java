package com.shopsphere.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CartItemResponse {

    private UUID id;

    private UUID productId;

    private String productName;

    private BigDecimal price;

    private int quantity;

    private BigDecimal subtotal;

}
