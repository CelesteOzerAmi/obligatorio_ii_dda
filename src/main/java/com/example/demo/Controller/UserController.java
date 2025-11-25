package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("users")
public class UserController {
    
    
    @Autowired
    private UserService userService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<UserEntity>> getAll () {
        return userService.getAll();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return userService.getById(id);
    }
    

    @PostMapping("postUser")
    public ResponseEntity<?> postUser(@RequestBody UserEntity user) {        
        return userService.postUser(user);
    }
    
    @PutMapping("updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserEntity user){
        return userService.updateUser(id, user);
    }
}
