package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.MovieRequest;
import com.example.demo.DTO.SeriesRequest;
import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.LibraryEntity;
import com.example.demo.Entity.MovieEntity;
import com.example.demo.Entity.SeriesEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.LibraryRepository;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    public ContentRepository contentRepository;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    LibraryRepository libraryRepository;

    @Override
    public ResponseEntity<ArrayList<ContentEntity>> getAll() {
        return ResponseEntity.ok().body(contentRepository.findAll());
    }

    @Override
    public ResponseEntity<ArrayList<MovieEntity>> getAllMovies() {
        ArrayList<ContentEntity> all = contentRepository.findAll();
        ArrayList<MovieEntity> moviesList = new ArrayList<>();

        for (ContentEntity c : all) {
            if (c instanceof MovieEntity movie) {
                moviesList.add(movie);
            }
        }
        return ResponseEntity.ok().body(moviesList);
    }

    @Override
    public ResponseEntity<ArrayList<SeriesEntity>> getAllSeries() {
        ArrayList<ContentEntity> all = contentRepository.findAll();
        ArrayList<SeriesEntity> seriesList = new ArrayList<>();

        for (ContentEntity c : all) {
            if (c instanceof SeriesEntity series) {
                seriesList.add(series);
            }
        }
        return ResponseEntity.ok().body(seriesList);
    }

    @Override
    public ResponseEntity<?> postMovie(MovieRequest movieRequest) {

        if (categoryService.getById(movieRequest.category().getName()) == null) {
            return new ResponseEntity<>("Categoría no existe.", HttpStatus.BAD_REQUEST);
        }
        MovieEntity movie = new MovieEntity();
        movie.setName(movieRequest.name());
        movie.setDescription(movieRequest.description());
        movie.setCategory(movieRequest.category());
        movie.setDuration(movieRequest.duration());
        movie.setYear(movieRequest.year());
        movie.setPurchasePrice(movieRequest.purchasePrice());
        movie.setRentPrice(movieRequest.rentPrice());
        movie.setPremiumExclusive(movieRequest.premiumExclusive());
        return new ResponseEntity<>(contentRepository.save(movie), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> postSeries(SeriesRequest seriesRequest) {
        if (categoryService.getById(seriesRequest.category().getName()) == null) {
            return new ResponseEntity<>("Categoría no existe.", HttpStatus.BAD_REQUEST);
        }
        SeriesEntity series = new SeriesEntity();
        series.setName(seriesRequest.name());
        series.setDescription(seriesRequest.description());
        series.setCategory(seriesRequest.category());
        series.setSeasons(seriesRequest.seasons());
        series.setChapters(seriesRequest.chapters());
        series.setYear(seriesRequest.year());
        series.setPurchasePrice(seriesRequest.purchasePrice());
        series.setRentPrice(seriesRequest.rentPrice());
        series.setPremiumExclusive(seriesRequest.premiumExclusive());
        return new ResponseEntity<>(contentRepository.save(series), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        ContentEntity contentRepo = contentRepository.findById(id).orElse(null);

        if (contentRepo == null) {
            return new ResponseEntity<>("No existe contenido con ese id.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contentRepo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteContent(int id) {
        ContentEntity contentRepo = contentRepository.findById(id).orElse(null);

        if (contentRepo == null) {
            return new ResponseEntity<>("No existe contenido con ese id.", HttpStatus.NOT_FOUND);
        }

        List<LibraryEntity> libraries = libraryRepository.findAll();
        for (LibraryEntity lib : libraries) {
            lib.getMovies().removeIf(m -> m.getId() == id);
            lib.getSeries().removeIf(s -> s.getId() == id);
        }
        libraryRepository.saveAll(libraries);

        contentRepository.delete(contentRepo);
        return new ResponseEntity<>("Contenido eliminado", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editMovie(int id, MovieRequest movieRequest) {
        ContentEntity movieRepo = contentRepository.findById(id).orElse(null);
        if (movieRepo != null) {
            movieRepo.setDescription(movieRequest.description());
            movieRepo.setYear(movieRequest.year());
            movieRepo.setCategory(movieRequest.category());
            movieRepo.setRentPrice(movieRequest.rentPrice());
            movieRepo.setPurchasePrice(movieRequest.purchasePrice());
            movieRepo.setPremiumExclusive(movieRequest.premiumExclusive());
            return new ResponseEntity<>(contentRepository.save(movieRepo), HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontró película con ese id", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> editSeries(int id, SeriesRequest seriesRequest) {
        ContentEntity seriesRepo = contentRepository.findById(id).orElse(null);
        if (seriesRepo != null) {
            seriesRepo.setDescription(seriesRequest.description());
            seriesRepo.setYear(seriesRequest.year());
            seriesRepo.setCategory(seriesRequest.category());
            seriesRepo.setRentPrice(seriesRequest.rentPrice());
            seriesRepo.setPurchasePrice(seriesRequest.purchasePrice());
            seriesRepo.setPremiumExclusive(seriesRequest.premiumExclusive());
            return new ResponseEntity<>(contentRepository.save(seriesRepo), HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontró serie con ese id", HttpStatus.NOT_FOUND);
    }
}
