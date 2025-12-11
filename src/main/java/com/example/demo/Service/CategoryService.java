package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.demo.DTO.CategoryRequest;
import com.example.demo.Entity.CategoryEntity;

public interface CategoryService {

    public ResponseEntity<ArrayList<CategoryEntity>> getAll();

    public ResponseEntity<?> postCategory(CategoryRequest categoryRequest);

    public ResponseEntity<?> getById(String name);

    public ResponseEntity<?> deleteCategory(String name);
    
}  