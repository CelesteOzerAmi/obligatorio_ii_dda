package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.SeriesEntity;
import com.example.demo.Service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("series")
public class SeriesController {
    
    @Autowired
    public ContentService contentService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<SeriesEntity>> getAll() {
        return contentService.getAllSeries();
    }

    @PostMapping("postSeries")
    public ResponseEntity<?> postSeries(@RequestBody SeriesEntity series) {        
        return contentService.postSeries(series);
    }
}
