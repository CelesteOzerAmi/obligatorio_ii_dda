package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Service.ContentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("content")
public class ContentController {
    
    @Autowired
    public ContentService contentService;

    @GetMapping("getAll")
    public ResponseEntity<ArrayList<ContentEntity>> getAll() {
        return contentService.getAll();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return contentService.getById(id);
    }

    @DeleteMapping("deleteContent/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable int id){
        return contentService.deleteContent(id);
    }
}
