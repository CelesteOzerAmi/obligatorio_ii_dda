package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.PurchaseEntity;
import com.example.demo.Entity.RentEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.PurchaseRepository;
import com.example.demo.Repository.RentRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    public PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    RentRepository rentRepository;

    @Override
    public ResponseEntity<ArrayList<PurchaseEntity>> getAll() {
        return ResponseEntity.ok().body(purchaseRepository.findAll());
    }

    @Override
    public ResponseEntity<?> postPurchase(int contentId, int userId) {
        ContentEntity contentRepo = contentRepository.findById(contentId).orElse(null);
        UserEntity userRepo = userRepository.findById(userId).orElse(null);

        if (contentRepo == null || userRepo == null) {
            return new ResponseEntity<>("Error al finalizar compra", HttpStatus.BAD_REQUEST);
        }

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setContent(contentRepo);
        purchase.setUser(userRepo);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setFinalPrice(contentRepo.getPurchasePrice());
        purchaseRepository.save(purchase);

        RentEntity rentRepo = rentRepository.findByUserIdAndContentId(userId, contentId);
        if (rentRepo != null) {
            rentRepository.delete(rentRepo);
            return new ResponseEntity<>(purchase, HttpStatus.ACCEPTED);
        }

        if (libraryService.addContent(userId, contentId)) {
            return new ResponseEntity<>(purchase, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(purchase, HttpStatus.ACCEPTED);
    }
}
