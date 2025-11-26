package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.LibraryEntity;
import com.example.demo.Repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService{
    
    @Autowired
    public LibraryRepository libraryRepository;

    @Override
    public ResponseEntity<?> getLibrary(int userId){
        List<LibraryEntity> all = libraryRepository.findAll();
        LibraryEntity library = all.stream()
            .filter(l -> l.getUser().getId() == userId)
            .findFirst()
            .orElse(null);

        if(library == null || library.getMovies().isEmpty() && library.getSeries().isEmpty()){
            return new ResponseEntity<>("Librería vacía", HttpStatus.OK);
        }
        return new ResponseEntity<>(library, HttpStatus.OK);
    }
}
