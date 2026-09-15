package com.shopsphere.cart;

import com.shopsphere.cart.dto.CartResponse;
import com.shopsphere.cart.mapper.CartItemMapper;
import com.shopsphere.cart.dto.AddCartItemRequest;
import com.shopsphere.cart.dto.CartItemResponse;
import com.shopsphere.cart.dto.UpdateCartItemRequest;
import com.shopsphere.cart.mapper.CartMapper;
import com.shopsphere.product.Product;
import com.shopsphere.product.ProductRepository;
import com.shopsphere.user.User;
import com.shopsphere.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final CartItemMapper cartItemMapper;

    private final CartMapper cartMapper;

    @Transactional(readOnly = true)
    public CartResponse getCart(UUID userId){

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found, hence unable to get cart"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(null);

        if(cart==null){
            return emptyCart();
        }

        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());

        List<CartItemResponse> responses = cartItemMapper.toResponseList(cartItems);

        return cartMapper.toResponse(cart, responses);
    }


    @Transactional
    public CartItemResponse addItem(
            UUID userId, AddCartItemRequest request
    ){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(()->{
                    Cart cart1 = new Cart();
                    cart1.setUser(user);
                    return cartRepository.save(cart1);
                });

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found"));

        if(!product.isActive()){
            throw new RuntimeException("Product is inactive");
        }

        Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        CartItem cartItem1;

        if(cartItem.isPresent()){
            cartItem1 = cartItem.get();

            cartItem1.setQuantity(cartItem1.getQuantity()+ request.getQuantity());
        }
        else{
            cartItem1 = cartItemMapper.toEntity(request, cart, product);
        }

        cartItem1 = cartItemRepository.save(cartItem1);

        return cartItemMapper.toResponse(cartItem1);
    }

    @Transactional
    public CartItemResponse updateItem(UUID userId, UUID cartItemId, UpdateCartItemRequest request){

        Cart cart = findCartByUserId(userId);

        CartItem cartItem = cartItemRepository.findByIdAndCartId(cartItemId, cart.getId())
                .orElseThrow(()-> new RuntimeException("CartItem not found"));

        cartItem.setQuantity(request.getQuantity());

        cartItem = cartItemRepository.save(cartItem);

        return cartItemMapper.toResponse(cartItem);
    }

    @Transactional
    public void removeItem(UUID userId, UUID cartItemId){

        Cart cart = findCartByUserId(userId);

        CartItem cartItem = cartItemRepository.findByIdAndCartId(cartItemId, cart.getId())
                        .orElseThrow(()-> new RuntimeException("Cart Item not found"));

        cartItemRepository.delete(cartItem);
    }

    private Cart findCartByUserId(UUID userId){
        return cartRepository.findByUserId(userId)
                .orElseThrow(()-> new RuntimeException("No cart associated with user"));
    }

    private CartResponse emptyCart(){

        CartResponse response = new CartResponse();
        response.setId(null);
        response.setItems(List.of());
        response.setTotal(BigDecimal.ZERO);

        return response;
    }
}
