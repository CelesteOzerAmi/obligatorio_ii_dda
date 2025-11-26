package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.PurchaseEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.PurchaseRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    
    @Autowired
    public PurchaseRepository purchaseRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ContentRepository contentRepository;

    @Override
    public ResponseEntity<ArrayList<PurchaseEntity>> getAll(){
        return ResponseEntity.ok().body(purchaseRepository.findAll());
    }

    @Override
    public ResponseEntity<?> postPurchase(PurchaseEntity purchase){
        if(purchase.getFinalPrice() < 1){
            return new ResponseEntity<>("Costo debe ser mayor a cero.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(purchaseRepository.save(purchase), HttpStatus.CREATED);
    }
}
