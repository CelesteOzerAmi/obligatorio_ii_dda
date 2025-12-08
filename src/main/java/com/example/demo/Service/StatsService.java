package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.StatsEntity;

public interface StatsService {
    public ResponseEntity<ArrayList<StatsEntity>> getAll();

    public ResponseEntity<ArrayList<StatsEntity>> getAllByUserId(int userId);

    public ResponseEntity<?> registerPlayback(Map<String, Object> body);

}
