package com.shopsphere.category;

import com.shopsphere.category.dto.CategoryResponse;
import com.shopsphere.category.dto.CreateCategoryRequest;
import com.shopsphere.category.dto.UpdateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest createRequest){

        return categoryService.createCategory(createRequest);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getById(@PathVariable UUID categoryId){
        return categoryService.getById(categoryId);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PatchMapping("/{categoryId}")
    public CategoryResponse updateCategory(@PathVariable UUID categoryId, @RequestBody UpdateCategoryRequest updateRequest){
        return categoryService.updateCategory(categoryId, updateRequest);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable UUID categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
