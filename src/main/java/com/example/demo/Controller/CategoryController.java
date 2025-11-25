package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("category")
public class CategoryController {
    
    @Autowired
    public CategoryService categoryService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<CategoryEntity>> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("getById/{name}")
    public ResponseEntity<?> getById(@PathVariable String name) {
        return categoryService.getById(name);
    }
    
    @PostMapping("postCategory")
    public ResponseEntity<?> postCategory(@RequestBody CategoryEntity category) {
        return categoryService.postCategory(category);
    }
    
}
