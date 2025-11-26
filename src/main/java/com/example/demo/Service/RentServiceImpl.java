package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.RentEntity;
import com.example.demo.Repository.RentRepository;

@Service
public class RentServiceImpl implements RentService{
    
    @Autowired
    public RentRepository rentRepository;

    @Override
    public ResponseEntity<ArrayList<RentEntity>> getAll(){
        return ResponseEntity.ok().body(rentRepository.findAll());
    }

    @Override
    public ResponseEntity<?> postRent(RentEntity rent){
        return new ResponseEntity<>(rentRepository.save(rent), HttpStatus.CREATED);
    }
}
