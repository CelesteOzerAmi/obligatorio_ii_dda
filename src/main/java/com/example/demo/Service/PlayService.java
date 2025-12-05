package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.PlayEntity;

public interface PlayService {
    public ResponseEntity<ArrayList<PlayEntity>> getAll();

    public ResponseEntity<ArrayList<PlayEntity>> getAllByUserId(int userId);

    public ResponseEntity<?> registerPlay(Map<String, Object> body);

}
