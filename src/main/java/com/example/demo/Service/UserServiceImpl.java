package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    static ArrayList<UserEntity> usersList = new ArrayList<>();

    @Override
    public ResponseEntity<ArrayList<UserEntity>> getAll(){
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @Override
    public ResponseEntity<?> postUser(UserEntity user){
        if(user.getName().isEmpty() || user.getEmail().isEmpty()){
            return ResponseEntity.badRequest().body("sin datos suficientes");
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getById(int id){
        Optional<UserEntity> user = userRepository.findById(id);

        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateUser(int id, UserEntity userEntity){

        Optional<UserEntity> userRepos = userRepository.findById(id);
        
        if(!userRepos.isPresent()){
            return new ResponseEntity<>("usuario no existe", HttpStatus.NOT_FOUND);
        }
        
        if(userEntity.getName().isEmpty() || userEntity.getEmail().isEmpty()){
            return new ResponseEntity<>("faltan datos obligatorios", HttpStatus.BAD_REQUEST);
        }

        UserEntity userFound = userRepos.get();
        userFound.setName(userEntity.getName());
        userFound.setEmail(userEntity.getEmail());

        return new ResponseEntity<>(userRepository.save(userFound), HttpStatus.OK);
    }
}
