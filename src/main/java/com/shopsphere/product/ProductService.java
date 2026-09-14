package com.shopsphere.product;

import com.shopsphere.category.Category;
import com.shopsphere.category.CategoryRepository;
import com.shopsphere.product.dto.CreateProductRequest;
import com.shopsphere.product.dto.ProductResponse;
import com.shopsphere.product.dto.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts(){

        List<Product> products = productRepository.findByActiveTrue();

        return productMapper.toResponseList(products);
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(UUID productId){
        Product product = findProduct(productId);

        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategory(UUID categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Product> products = productRepository.findByCategoryId(categoryId);

        return productMapper.toResponseList(products);
    }

    public List<ProductResponse> getProductsByName(String name){
        name = name.trim().toLowerCase();
        List<Product> products = productRepository.findByNameContainingIgnoreCaseAndActiveTrue(name);

        return productMapper.toResponseList(products);
    }

    @Transactional
    public ProductResponse createProduct(CreateProductRequest createRequest){

        productRepository.findBySku(createRequest.getSku())
                .ifPresent(product -> {
                    throw new RuntimeException("Sku already associated with other product");
                });

        Category category = categoryRepository.findById(createRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = productMapper.toEntity(createRequest, category);

        product = productRepository.save(product);

        return productMapper.toResponse(product);
    }

    @Transactional
    public ProductResponse updateProduct(UUID productId, UpdateProductRequest updateRequest){

        Product product = findProduct(productId);

        if(updateRequest.getSku()!=null && !updateRequest.getSku().isBlank()){
            productRepository.findBySku(updateRequest.getSku())
                    .ifPresent(existingProduct -> {
                        if(!existingProduct.getId().equals(productId)){
                            throw new RuntimeException("Sku already associated with other product");
                        }
                    });
            product.setSku(updateRequest.getSku());
        }

        if(updateRequest.getName()!=null && !updateRequest.getName().isBlank()){
            product.setName(updateRequest.getName());
        }

        if(updateRequest.getDescription()!=null && !updateRequest.getDescription().isBlank()){
            product.setDescription(updateRequest.getDescription());
        }

        if(updateRequest.getCategoryId()!=null){
            Category category = categoryRepository.findById(updateRequest.getCategoryId())
                            .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        if(updateRequest.getPrice()!=null){
            product.setPrice(updateRequest.getPrice());
        }

        if(updateRequest.getActive()!=null){
            product.setActive(updateRequest.getActive());
        }

        product = productRepository.save(product);

        return productMapper.toResponse(product);
    }


    private Product findProduct(UUID productId){
        return productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product not found"));
    }

}
