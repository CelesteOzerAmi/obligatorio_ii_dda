package com.example.demo.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.StatsEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.StatsRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public ResponseEntity<ArrayList<StatsEntity>> getAll() {
        return new ResponseEntity<>(statsRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArrayList<StatsEntity>> getAllByUserId(int userId) {
        return new ResponseEntity<>(statsRepository.findAllByUserId(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registerPlayback(Map<String, Object> body) {
        int userId = (int) body.get("userId");
        UserEntity userRepo = userRepository.findById(userId).orElse(null);
        
        int contentId = (int) body.get("contentId");
        ContentEntity contentRepo = contentRepository.findById(contentId).orElse(null);

        if(userRepo == null || contentRepo == null){
            return new ResponseEntity<>("Error al registrar reproducción", HttpStatus.BAD_REQUEST);
        }
        try {
            
            LocalDateTime date = LocalDateTime.parse(body.get("date").toString());
    
            String durationToParse = body.get("duration").toString();
            Duration duration = Duration.parse(durationToParse);
    
            int rate = (int) body.get("rate");
    
            StatsEntity playback = new StatsEntity();
            playback.setUser(userRepo);
            playback.setContent(contentRepo);
            playback.setDate(date);
            playback.setDuration(duration);
            playback.setRate(rate);
            return new ResponseEntity<>(statsRepository.save(playback), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
