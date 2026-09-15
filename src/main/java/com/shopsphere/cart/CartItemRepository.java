package com.shopsphere.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    List<CartItem> findByCartId(UUID cartId);

    Optional<CartItem> findByIdAndCartId(UUID cartId, UUID cartItemId);

    Optional<CartItem> findByCartIdAndProductId(UUID cartId, UUID productId);
}
