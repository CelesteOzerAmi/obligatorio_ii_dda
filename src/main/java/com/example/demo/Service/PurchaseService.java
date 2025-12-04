package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.PurchaseEntity;

public interface PurchaseService {
    
    public ResponseEntity<ArrayList<PurchaseEntity>> getAll();

    public ResponseEntity<?> postPurchase(int contentId, int userId);
}
