package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.LibraryEntity;
import com.example.demo.Entity.MovieEntity;
import com.example.demo.Entity.SeriesEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService{
    
    @Autowired
    public LibraryRepository libraryRepository;

    @Autowired
    public ContentRepository contentRepository;

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

    @Override
    public ResponseEntity<?> addContent(int userId, int contentId){
        List<LibraryEntity> all = libraryRepository.findAll();
        LibraryEntity library = all.stream()
            .filter(l -> l.getUser().getId() == userId)
            .findFirst()
            .orElse(null);

        ContentEntity content = contentRepository.findById(contentId).orElse(null);

        if(content != null){
            if(content instanceof MovieEntity movie){
                ArrayList<MovieEntity> moviesList = library.getMovies();
                moviesList.add(movie);
                library.setMovies(moviesList);
            } else if(content instanceof SeriesEntity series){
                ArrayList<SeriesEntity> seriesList = library.getSeries();
                seriesList.add(series);
                library.setSeries(seriesList);
            }
        } else {
            return new ResponseEntity<>("Contenido no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(libraryRepository.save(library), HttpStatus.ACCEPTED);
    }
}
