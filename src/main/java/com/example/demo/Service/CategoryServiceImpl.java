package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ArrayList<CategoryEntity>> getAll(){
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    @Override
    public ResponseEntity<?> postCategory(CategoryEntity category){
        if(category.getName().isBlank()){
            return new ResponseEntity<>("Ingrese el nombre de la categoría", HttpStatus.BAD_REQUEST);
        }
        if(categoryRepository.findById(category.getName()).isPresent()){
            return new ResponseEntity<>("Categoría ya existe", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(String name){
        CategoryEntity categoryRepos = categoryRepository.findById(name).orElse(null);

        if(categoryRepos == null){
            return new ResponseEntity<>("Categoría no existe", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoryRepos, HttpStatus.OK);
        }        
    }
    
}
