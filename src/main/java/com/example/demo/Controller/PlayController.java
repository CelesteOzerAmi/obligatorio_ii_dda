package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.PlayEntity;
import com.example.demo.Service.PlayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("stats")
public class PlayController {
    
    @Autowired
    private PlayService playService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<PlayEntity>> getAll() {
        return playService.getAll();
    }

    @GetMapping("getAll/{userId}")
    public ResponseEntity<ArrayList<PlayEntity>> getAllByUserId (@PathVariable int userId) {
        return playService.getAllByUserId(userId);
    }
    
    @PostMapping("registerPlay")
    public ResponseEntity<?> registerPlay(@RequestBody Map<String, Object> body) {
        return playService.registerPlay(body);
    }
    
    
}
