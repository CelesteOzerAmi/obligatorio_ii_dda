package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.RentEntity;
import com.example.demo.Entity.SubscriptionEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.RentRepository;
import com.example.demo.Repository.SubscriptionRepository;
import com.example.demo.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public ResponseEntity<ArrayList<RentEntity>> getAll() {
        return ResponseEntity.ok().body(rentRepository.findAll());
    }

    @Transactional
    @Override
    public ResponseEntity<?> postRent(int contentId, int userId) {
        ContentEntity contentRepo = contentRepository.findById(contentId).orElse(null);
        UserEntity userRepo = userRepository.findById(userId).orElse(null);
        if (contentRepo == null || userRepo == null) {
            return new ResponseEntity<>("No fue posible concretar la operación.", HttpStatus.BAD_REQUEST);
        }

        RentEntity rentRepo = getByUserIdAndContentId(userId, contentId);
        if (rentRepo != null) {
            rentRepo.setExpirationDate(LocalDate.now().plusDays(30));
            rentRepository.save(rentRepo);
            return new ResponseEntity<>(rentRepo, HttpStatus.OK);
        }

        RentEntity rent = new RentEntity();
        rent.setContent(contentRepo);
        rent.setUser(userRepo);
        rent.setRentDate(LocalDate.now());
        rent.setExpirationDate(LocalDate.now().plusDays(30));

        SubscriptionEntity subscriptionEntity = subscriptionRepository.findAll()
                .stream()
                .filter(s -> s.getUser().getId() == userRepo.getId() && s.isActive())
                .findFirst()
                .orElse(null);

        if (subscriptionEntity != null) {
            rent.setFinalPrice(contentRepo.getRentPrice() * subscriptionEntity.getPremiumDiscount());
        } else {
            rent.setFinalPrice(contentRepo.getRentPrice());
        }

        rentRepository.save(rent);
        if (libraryService.addContent(userId, contentId)) {
            return new ResponseEntity<>(rent, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Hubo un error al agregar contenido", HttpStatus.BAD_REQUEST);
    }

    @Override
    public RentEntity getByUserIdAndContentId(int userId, int contentId) {
        return rentRepository.findByUserIdAndContentId(userId, contentId);
    }

}
