package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {
    
    public ArrayList<PurchaseEntity> findAll();
}
