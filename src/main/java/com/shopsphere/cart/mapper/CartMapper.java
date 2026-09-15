package com.shopsphere.cart.mapper;

import com.shopsphere.cart.Cart;
import com.shopsphere.cart.dto.CartItemResponse;
import com.shopsphere.cart.dto.CartResponse;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartMapper {

    public CartResponse toResponse(
            Cart cart,
            List<CartItemResponse> items
    ){
        CartResponse response = new CartResponse();

        response.setId(cart.getId());
        response.setItems(items);

        BigDecimal total = items.stream()
                .map(CartItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        response.setTotal(total);

        return response;
    }
}
