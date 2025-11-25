package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.CategoryEntity;

public interface CategoryService {

    public ResponseEntity<ArrayList<CategoryEntity>> getAll();

    public ResponseEntity<?> postCategory(CategoryEntity category);

    public ResponseEntity<?> getById(String name);
    
}  