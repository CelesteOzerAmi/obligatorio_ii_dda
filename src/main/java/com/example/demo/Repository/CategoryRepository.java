package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String>{

    public ArrayList<CategoryEntity> findAll();  
} 