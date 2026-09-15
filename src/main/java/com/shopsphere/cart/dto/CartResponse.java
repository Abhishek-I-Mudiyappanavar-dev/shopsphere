package com.shopsphere.cart.dto;

import com.shopsphere.cart.CartItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CartResponse {

    private UUID id;

    private List<CartItemResponse> items;

    private BigDecimal total;
}
