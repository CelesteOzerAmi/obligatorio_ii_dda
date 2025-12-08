package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.MovieRequest;
import com.example.demo.Entity.MovieEntity;
import com.example.demo.Service.ContentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("movies")
public class MovieController {
    
    @Autowired
    public ContentService contentService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<MovieEntity>> getAll() {
        return contentService.getAllMovies();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return contentService.getById(id);
    }
    
    @PostMapping("postMovie")
    public ResponseEntity<?> postMovie(@Valid @RequestBody MovieRequest movieRequest) {        
        return contentService.postMovie(movieRequest);
    }
    
    @PutMapping("editMovie/{id}")
    public ResponseEntity<?> editMovie(@PathVariable int id, @Valid @RequestBody MovieRequest movieRequest){
        return contentService.editMovie(id, movieRequest);
    }
}