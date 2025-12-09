package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.RentEntity;

public interface RentRepository extends JpaRepository<RentEntity, Integer> {
    
    public ArrayList<RentEntity> findAll();

}
