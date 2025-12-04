package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.RentEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.RentRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class RentServiceImpl implements RentService{
    
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    public LibraryService libraryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public ResponseEntity<ArrayList<RentEntity>> getAll(){
        return ResponseEntity.ok().body(rentRepository.findAll());
    }

   @Override
    public ResponseEntity<?> postRent(int contentId, int userId){
        ContentEntity contentRepo = contentRepository.findById(contentId).orElse(null);
        UserEntity userRepo = userRepository.findById(userId).orElse(null);
        if(contentRepo == null || userRepo == null){
            return new ResponseEntity<>("No fue posible concretar la operación.", HttpStatus.BAD_REQUEST);
        }
        RentEntity rent = new RentEntity();
        rent.setContent(contentRepo);
        rent.setUser(userRepo);
        rent.setRentDate(LocalDate.now());
        rent.setExpirationDate(LocalDate.now().plusDays(30));
        rent.setFinalPrice(contentRepo.getRentPrice());
        return new ResponseEntity<>(rentRepository.save(rent), HttpStatus.ACCEPTED);
    }
}
