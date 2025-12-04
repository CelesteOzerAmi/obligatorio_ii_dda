package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.RentEntity;

import jakarta.transaction.Transactional;

public interface RentService {
    
    public ResponseEntity<ArrayList<RentEntity>> getAll();

    @Transactional
    public ResponseEntity<?> postRent(int contentId, int userId);
}
