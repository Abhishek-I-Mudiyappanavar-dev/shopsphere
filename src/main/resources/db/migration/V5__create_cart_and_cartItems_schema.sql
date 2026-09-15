
CREATE TABLE carts(
    id UUID PRIMARY KEY,

    user_id UUID NOT NULL UNIQUE,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),

    CONSTRAINT fk_cart_belongs_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);


CREATE TABLE cart_items (
    id UUID PRIMARY KEY,

    cart_id UUID NOT NULL,
    product_id UUID NOT NULL,

    quantity INTEGER NOT NULL,

    CONSTRAINT fk_cart_item_belongs_cart
        FOREIGN KEY (cart_id)
        REFERENCES carts(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_cart_item_product
        FOREIGN KEY (product_id)
        REFERENCES products(id),

    CONSTRAINT chk_cart_item_quantity_positive
        CHECK (quantity > 0),

    CONSTRAINT uk_cart_product
        UNIQUE (cart_id, product_id)
);