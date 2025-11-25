package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.ContentEntity;

public interface ContentRepository extends JpaRepository<ContentEntity, Integer> {
    
    public ArrayList<ContentEntity> findAll();
}
