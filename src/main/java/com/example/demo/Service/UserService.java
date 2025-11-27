package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.UserEntity;

public interface UserService {
    
    public ResponseEntity<ArrayList<UserEntity>> getAll();

    public ResponseEntity<ArrayList<UserEntity>> getPremiumUsers();

    public ResponseEntity<ArrayList<UserEntity>> getStandardUsers();
    
    public ResponseEntity<?> postUser(UserEntity user);

    public ResponseEntity<?> getById(int id);

    public ResponseEntity<?> updateUser(int id, UserEntity user);

}
