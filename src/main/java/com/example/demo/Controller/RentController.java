package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.RentEntity;
import com.example.demo.Service.RentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.DTO.RentRequest;


@RestController
@RequestMapping("rent")
public class RentController {
    
    @Autowired
    public RentService rentService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<RentEntity>> getAll() {
        return rentService.getAll();
    }

    @PostMapping("rentContent/{contentId}")
    public ResponseEntity<?> postRent(@PathVariable int contentId, @Valid @RequestBody RentRequest request) {
        return rentService.postRent(contentId, request.userId());
    }
}
