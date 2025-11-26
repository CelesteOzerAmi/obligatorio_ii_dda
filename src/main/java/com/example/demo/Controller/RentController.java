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
import com.example.demo.Entity.RentEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.RentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("rent")
public class RentController {
    
    @Autowired
    public RentService rentService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ContentRepository contentRepository;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<RentEntity>> getAll() {
        return rentService.getAll();
    }

    @PostMapping("rentContent")
    public ResponseEntity<?> postRent(@RequestBody Map<String, Object> body) {
        int userId = (int) body.get("user");
        int contentId = (int) body.get("content");
        String dateOfRent = body.get("rentDate").toString();
        LocalDate rentDate = LocalDate.parse(dateOfRent);
        String dateOfExp = body.get("expirationDate").toString();
        LocalDate expirationDate = LocalDate.parse(dateOfExp);
        double finalPrice = Double.parseDouble(body.get("finalPrice").toString());

        UserEntity userRepo = userRepository.findById(userId).orElse(null);
        ContentEntity contentRepo = contentRepository.findById(contentId).orElse(null);

        if(userRepo == null || contentRepo == null){
            return new ResponseEntity<>("Usuario o contenido no existe", HttpStatus.BAD_REQUEST);
        }


        RentEntity rent = new RentEntity();
        rent.setUser(userRepo);
        rent.setContent(contentRepo);
        rent.setRentDate(rentDate);
        rent.setExpirationDate(expirationDate);
        rent.setFinalPrice(finalPrice);

        return rentService.postRent(rent);
    }
    
    
}
