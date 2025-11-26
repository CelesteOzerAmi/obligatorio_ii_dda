package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.RentEntity;

public interface RentService {
    
    public ResponseEntity<ArrayList<RentEntity>> getAll();

    public ResponseEntity<?> postRent(RentEntity rent);
}
