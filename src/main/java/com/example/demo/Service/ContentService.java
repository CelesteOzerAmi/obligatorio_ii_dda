package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.demo.DTO.MovieRequest;
import com.example.demo.DTO.SeriesRequest;
import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.MovieEntity;
import com.example.demo.Entity.SeriesEntity;

public interface ContentService {
    
    public ResponseEntity<ArrayList<ContentEntity>> getAll();

    public ResponseEntity<?> getById(int id);

    public ResponseEntity<ArrayList<MovieEntity>> getAllMovies();

    public ResponseEntity<?> postMovie(MovieRequest movieRequest);

    public ResponseEntity<ArrayList<SeriesEntity>> getAllSeries();

    public ResponseEntity<?> postSeries(SeriesRequest seriesRequest);

    public ResponseEntity<?> deleteContent(int id);

    public ResponseEntity<?> editMovie(int id, MovieRequest movieRequest);

    public ResponseEntity<?> editSeries(int id, SeriesRequest seriesRequest);
}
