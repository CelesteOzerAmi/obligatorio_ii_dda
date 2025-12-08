package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.SubscriptionEntity;
import com.example.demo.Service.SubscriptionService;

@RestController
@RequestMapping("streaming/premium")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<SubscriptionEntity>> getAll(){
        return subscriptionService.getAll();
    }

    @PostMapping("bePremium/{userId}")
    public ResponseEntity<?> postSubscription(@PathVariable int userId){
        return subscriptionService.postSubscription(userId);
    }

    @PutMapping("cancelSubscription/{userId}")
    public ResponseEntity<?> cancelSubscription(@PathVariable int userId){
        return subscriptionService.cancelSubscription(userId);
    }
    
}
