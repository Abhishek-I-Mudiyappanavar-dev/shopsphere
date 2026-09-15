package com.shopsphere.cart.mapper;

import com.shopsphere.cart.Cart;
import com.shopsphere.cart.CartItem;
import com.shopsphere.cart.dto.AddCartItemRequest;
import com.shopsphere.cart.dto.CartItemResponse;
import com.shopsphere.product.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartItemMapper {

    public CartItem toEntity(AddCartItemRequest addCartItemRequest, Cart cart, Product product){

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(addCartItemRequest.getQuantity());

        return cartItem;
    }

    public CartItemResponse toResponse(CartItem cartItem){

        CartItemResponse response = new CartItemResponse();

        Product product = cartItem.getProduct();

        response.setId(cartItem.getId());
        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(cartItem.getQuantity());
        response.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));

        return response;
    }

    public List<CartItemResponse> toResponseList(List<CartItem> cartItems){

        return cartItems.stream()
                .map(this::toResponse)
                .toList();
    }
}
