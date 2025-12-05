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
import com.example.demo.Entity.PlayEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.PlayRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class PlayServiceImpl implements PlayService {

    @Autowired
    private PlayRepository playRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public ResponseEntity<ArrayList<PlayEntity>> getAll() {
        return new ResponseEntity<>(playRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArrayList<PlayEntity>> getAllByUserId(int userId) {
        return new ResponseEntity<>(playRepository.findAllByUserId(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registerPlay(Map<String, Object> body) {
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
    
            PlayEntity play = new PlayEntity();
            play.setUser(userRepo);
            play.setContent(contentRepo);
            play.setDate(date);
            play.setDuration(duration);
            play.setRate(rate);
            return new ResponseEntity<>(playRepository.save(play), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
