package com.shopsphere.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateCartItemRequest {

    @Min(1)
    private int quantity;
}
