package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.LibraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("streaming/library")
public class LibraryController {
    
    @Autowired
    public LibraryService libraryService;

    @GetMapping("getLibrary/{userId}")
    public ResponseEntity<?> getLibrary(@PathVariable int userId) {
        return libraryService.getLibrary(userId);
    }
        
}
