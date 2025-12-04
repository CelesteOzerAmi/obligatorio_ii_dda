package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.MovieEntity;
import com.example.demo.Service.ContentService;
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
    public ResponseEntity<?> postMovie(@RequestBody MovieEntity movie) {        
        return contentService.postMovie(movie);
    }
    
    @PutMapping("editMovie")
    public ResponseEntity<?> editMovie(@RequestBody MovieEntity movie){
        return contentService.editMovie(movie);
    }
}