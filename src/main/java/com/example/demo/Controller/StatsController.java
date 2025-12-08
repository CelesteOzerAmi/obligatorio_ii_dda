package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.StatsEntity;
import com.example.demo.Service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("streaming/stats")
public class StatsController {
    
    @Autowired
    private StatsService statsService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<StatsEntity>> getAll() {
        return statsService.getAll();
    }

    @GetMapping("getAll/{userId}")
    public ResponseEntity<ArrayList<StatsEntity>> getAllByUserId (@PathVariable int userId) {
        return statsService.getAllByUserId(userId);
    }
    
    @PostMapping("registerPlay")
    public ResponseEntity<?> registerPlayback(@RequestBody Map<String, Object> body) {
        return statsService.registerPlayback(body);
    }
    
    
}
