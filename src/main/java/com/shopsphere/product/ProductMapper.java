package com.shopsphere.product;

import com.shopsphere.category.Category;
import com.shopsphere.product.dto.CreateProductRequest;
import com.shopsphere.product.dto.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest createRequest, Category category){

        Product product = new Product();

        product.setSku(createRequest.getSku());
        product.setName(createRequest.getName());
        product.setDescription(createRequest.getDescription());
        product.setCategory(category);
        product.setPrice(createRequest.getPrice());

        return product;
    }

    public ProductResponse toResponse(Product product){

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setSku(product.getSku());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory().getName());
        response.setPrice(product.getPrice());
        response.setActive(product.isActive());

        return response;
    }

    public List<ProductResponse> toResponseList(List<Product> products){

        return products.stream()
                .map(this::toResponse)
                .toList();
    }
}
