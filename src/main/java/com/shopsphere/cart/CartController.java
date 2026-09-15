package com.shopsphere.cart;

import com.shopsphere.cart.dto.AddCartItemRequest;
import com.shopsphere.cart.dto.CartItemResponse;
import com.shopsphere.cart.dto.CartResponse;
import com.shopsphere.cart.dto.UpdateCartItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/{userId}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartResponse getCart(@PathVariable UUID userId){
        return cartService.getCart(userId);
    }

    @PostMapping("/items")
    public CartItemResponse addItem(
            @PathVariable UUID userId,
            @Valid @RequestBody AddCartItemRequest request
    ){
        return cartService.addItem(userId, request);
    }

    @PatchMapping("/items/{cartItemId}")
    public CartItemResponse updateItem(
            @PathVariable UUID userId,
            @PathVariable UUID cartItemId,
            @Valid @RequestBody UpdateCartItemRequest request
    ){
        return cartService.updateItem(userId, cartItemId, request);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void removeItem(
            @PathVariable UUID userId,
            @PathVariable UUID cartItemId
    ){
        cartService.removeItem(userId, cartItemId);
    }
}
