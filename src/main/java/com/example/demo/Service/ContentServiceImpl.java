package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.Controller.CategoryController;
import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.MovieEntity;
import com.example.demo.Entity.SeriesEntity;
import com.example.demo.Repository.ContentRepository;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    public ContentRepository contentRepository;

    @Autowired
    CategoryServiceImpl categoryService;

    @Override
    public ResponseEntity<ArrayList<ContentEntity>> getAll() {
        return ResponseEntity.ok().body(contentRepository.findAll());
    }

    @Override
    public ResponseEntity<ArrayList<MovieEntity>> getAllMovies(){
        ArrayList<ContentEntity> all = contentRepository.findAll();
        ArrayList<MovieEntity> moviesList = new ArrayList<>();

        for(ContentEntity c : all){
            if(c instanceof MovieEntity movie){
                moviesList.add(movie);
            }
        }
        return ResponseEntity.ok().body(moviesList);
    }

    @Override
    public ResponseEntity<ArrayList<SeriesEntity>> getAllSeries(){
        ArrayList<ContentEntity> all = contentRepository.findAll();
        ArrayList<SeriesEntity> seriesList = new ArrayList<>();

        for(ContentEntity c : all){
            if(c instanceof SeriesEntity series){
                seriesList.add(series);
            }
        }
        return ResponseEntity.ok().body(seriesList);
    }

    @Override
    public ResponseEntity<?> postMovie(MovieEntity movie){
        if(movie.getName().isBlank() || movie.getCategory().equals(null) || 
            movie.getDescription().isBlank() || movie.getYear().isBlank() ||
            movie.getDuration() < 1) {
            return new ResponseEntity<>("Faltan datos", HttpStatus.BAD_REQUEST);
        }
        if (movie.getPurchasePrice() < 0 || movie.getRentPrice() < 0) {
            return new ResponseEntity<>("Costos de compra o alquiler deben ser mayores a cero.",
                    HttpStatus.BAD_REQUEST);
        }
        

        if(categoryService.getById(movie.getCategory().getName()) == null){
            return new ResponseEntity<>("Categoría no existe.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contentRepository.save(movie), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> postSeries(SeriesEntity series){
        if(series.getName().isBlank() || series.getCategory().equals(null) || 
            series.getDescription().isBlank() || series.getYear().isBlank() || 
            series.getSeasons() < 1 || series.getChapters() < 1) {
            return new ResponseEntity<>("Faltan datos", HttpStatus.BAD_REQUEST);
        }
        if (series.getPurchasePrice() < 0 || series.getRentPrice() < 0) {
            return new ResponseEntity<>("Costos de compra o alquiler deben ser mayores a cero.",
                    HttpStatus.BAD_REQUEST);
        }
        

        if(categoryService.getById(series.getCategory().getName()) == null){
            return new ResponseEntity<>("Categoría no existe.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contentRepository.save(series), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        ContentEntity contentRepo = contentRepository.findById(id).orElse(null);

        if (contentRepo == null) {
            return new ResponseEntity<>("No existe contenido con ese id.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contentRepo, HttpStatus.OK);
    }

}
