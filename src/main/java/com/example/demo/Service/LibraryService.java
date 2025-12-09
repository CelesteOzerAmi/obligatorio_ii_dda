package com.example.demo.Service;

import org.springframework.http.ResponseEntity;

public interface LibraryService {
    
    public ResponseEntity<?> getLibrary(int userId);

    public boolean addContent(int userId, int contentId);

    public ResponseEntity<?> getContentOnLibrary(int userId, int contentId);

    public ResponseEntity<?> returnContent(int userId);

    public ResponseEntity<?> removeContent(int userId, int contentId);
}
