package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.SubscriptionEntity;

public interface SubscriptionService {
    
    public ResponseEntity<ArrayList<SubscriptionEntity>> getAll();

    public ResponseEntity<?> postSubscription(int userId);

    public ResponseEntity<?> cancelSubscription(int userId);
}
