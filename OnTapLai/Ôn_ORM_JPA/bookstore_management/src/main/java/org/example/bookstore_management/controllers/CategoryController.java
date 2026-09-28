package org.example.bookstore_management.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bookstore_management.entities.Category;
import org.example.bookstore_management.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    //Lấy theo id
    @GetMapping("{id}")
    public Category getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    //Thêm danh mục
    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    //Cập nhật danh mục
    @PutMapping("{id}")
    public Category updateCategory(
            @PathVariable long id,
            @RequestBody Category category
    ){
        return categoryService.updateCategory(id, category);
    }

    //Xóa danh mục
    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
    }

}
