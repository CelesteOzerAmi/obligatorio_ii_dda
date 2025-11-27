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

    @Autowired
    public LibraryService libraryService;

    @Override
    public ResponseEntity<ArrayList<RentEntity>> getAll(){
        return ResponseEntity.ok().body(rentRepository.findAll());
    }

    @Override
    public ResponseEntity<?> postRent(RentEntity rent){
        rentRepository.save(rent);
        libraryService.addContent(rent.getUser().getId(), rent.getContent().getId());
        return new ResponseEntity<>(rent, HttpStatus.CREATED);
    }
}
