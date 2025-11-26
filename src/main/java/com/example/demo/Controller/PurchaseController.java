package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.PurchaseEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.PurchaseService;

import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("postPurchase")
    public ResponseEntity<?> postPurchase(@RequestBody Map<String, Object> body) {
        
        int userId = (int) body.get("user");
        int contentId = (int) body.get("content");
        String date = body.get("purchaseDate").toString();
        LocalDate purchaseDate = LocalDate.parse(date);
        double finalPrice = Double.parseDouble(body.get("finalPrice").toString());

        UserEntity userRepo = userRepository.findById(userId).orElse(null);
        ContentEntity contentRepo = contentRepository.findById(contentId).orElse(null);

        if(userRepo == null || contentRepo == null){
            return new ResponseEntity<>("Usuario o contenido no existe", HttpStatus.BAD_REQUEST);
        }

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setUser(userRepo);
        purchase.setContent(contentRepo);
        purchase.setPurchaseDate(purchaseDate);
        purchase.setFinalPrice(finalPrice);

        return purchaseService.postPurchase(purchase);
    }
}
