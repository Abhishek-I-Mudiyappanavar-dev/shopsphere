package com.shopsphere.product;

import com.shopsphere.product.dto.CreateProductRequest;
import com.shopsphere.product.dto.ProductResponse;
import com.shopsphere.product.dto.UpdateProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ProductResponse getById(@PathVariable UUID productId){
        return productService.getById(productId);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/name")
    public List<ProductResponse> getProductsByName(@RequestParam String name){
        return productService.getProductsByName(name);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> getProductsByCategoryId(@PathVariable UUID categoryId){
        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody CreateProductRequest createRequest){
        return productService.createProduct(createRequest);
    }

    @PatchMapping("/{productId}")
    public ProductResponse updateProduct(@PathVariable UUID productId,@RequestBody UpdateProductRequest updateRequest){
        return productService.updateProduct(productId, updateRequest);
    }

}
