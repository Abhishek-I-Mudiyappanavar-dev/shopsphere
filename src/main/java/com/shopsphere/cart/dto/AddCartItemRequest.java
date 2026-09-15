package com.shopsphere.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class AddCartItemRequest {

    @NotNull
    private UUID productId;

    @Min(1)
    private int quantity;

}
