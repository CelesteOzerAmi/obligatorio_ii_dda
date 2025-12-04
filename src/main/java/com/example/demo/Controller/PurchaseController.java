package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.PurchaseRequest;
import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.PurchaseEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.PurchaseService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("buy")
public class PurchaseController {
    
    @Autowired
    public PurchaseService purchaseService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ContentRepository contentRepository;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<PurchaseEntity>> getAll() {
        return purchaseService.getAll();
    }

    @PostMapping("buyContent/{contentId}")
    public ResponseEntity<?> postPurchase(@PathVariable int contentId, @Valid @RequestBody PurchaseRequest purchaseReq) {
        return purchaseService.postPurchase(contentId, purchaseReq.userId());
    }
}
