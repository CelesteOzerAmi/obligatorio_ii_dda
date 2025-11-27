package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SubscriptionEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.SubscriptionRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    
    @Autowired
    public SubscriptionRepository subscriptionRepository;

    @Autowired
    public UserRepository userRepository;

    @Override
    public ResponseEntity<ArrayList<SubscriptionEntity>> getAll(){
        return ResponseEntity.ok().body(subscriptionRepository.findAll());
    }

    @Override
    public ResponseEntity<?> postSubscription(int userId){
        UserEntity userRepo = userRepository.findById(userId).orElse(null);

        if(userRepo == null){
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        SubscriptionEntity subscriptionRepo = subscriptionRepository.findAll().stream()
            .filter(s -> s.getUser().equals(userRepo) && s.isActive())
            .findFirst()
            .orElse(null);
        
        if(subscriptionRepo != null){
            return new ResponseEntity<>("Usuario ya es premium", HttpStatus.OK);
        }

        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setUser(userRepo);
        subscription.setActivationDate(LocalDate.now());
        subscription.setActive(true);
        return new ResponseEntity<>(subscriptionRepository.save(subscription), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> cancelSubscription(int userId){
        SubscriptionEntity subscriptionRepo = subscriptionRepository.findAll().stream()
            .filter(s -> s.getUser().getId() == userId && s.isActive())
            .findFirst()
            .orElse(null);

        if(subscriptionRepo == null){
            return new ResponseEntity<>("Suscripción no encontrada", HttpStatus.NOT_FOUND);
        }
        
        subscriptionRepository.delete(subscriptionRepo);
        return new ResponseEntity<>("Suscripción cancelada con éxito", HttpStatus.OK);
    }
}
