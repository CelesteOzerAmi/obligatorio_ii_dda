package com.example.demo.Service;

import org.springframework.http.ResponseEntity;

public interface LibraryService {
    
    public ResponseEntity<?> getLibrary(int userId);
}
