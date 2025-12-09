package com.example.demo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContentEntity;
import com.example.demo.Entity.LibraryEntity;
import com.example.demo.Entity.MovieEntity;
import com.example.demo.Entity.RentEntity;
import com.example.demo.Entity.SeriesEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.LibraryRepository;
import com.example.demo.Repository.RentRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    public LibraryRepository libraryRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RentRepository rentRepository;

    @Override
    public ResponseEntity<?> getLibrary(int userId) {

        try {
            UserEntity userRepo = userRepository.findById(userId).orElse(null);
            if (userRepo == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.BAD_REQUEST);
            }

            LibraryEntity library = libraryRepository.findByUserId(userId);

            if (library == null) {
                library = new LibraryEntity();
                library.setUser(userRepo);
                library.setMovies(new ArrayList<MovieEntity>());
                library.setSeries(new ArrayList<SeriesEntity>());
                libraryRepository.save(library);
            }

            return new ResponseEntity<>(library, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public boolean addContent(int userId, int contentId) {

        LibraryEntity library = libraryRepository.findByUserId(userId);
        if (library == null) {
            getLibrary(userId);
        }
        ContentEntity content = contentRepository.findById(contentId).orElse(null);

        if (content != null) {
            if (content instanceof MovieEntity movie) {
                List<MovieEntity> moviesList = library.getMovies();
                moviesList.add(movie);
                library.setMovies(moviesList);
            } else if (content instanceof SeriesEntity series) {
                List<SeriesEntity> seriesList = library.getSeries();
                seriesList.add(series);
                library.setSeries(seriesList);
            }
        } else {
            new ResponseEntity<>("Contenido no encontrado", HttpStatus.NOT_FOUND);
            return false;
        }
        new ResponseEntity<>(libraryRepository.save(library), HttpStatus.ACCEPTED);
        return true;
    }

    @Override
    public ResponseEntity<?> getContentOnLibrary(int userId, int contentId) {

        LibraryEntity libraryRepo = libraryRepository.findByUserId(userId);
        if (libraryRepo != null) {
            MovieEntity movie = libraryRepo.getMovies().stream()
                    .filter(m -> m.getId() == contentId)
                    .findFirst()
                    .orElse(null);
            if (movie != null) {
                return new ResponseEntity<>(movie, HttpStatus.OK);
            }

            SeriesEntity series = libraryRepo.getSeries().stream()
                    .filter(s -> s.getId() == contentId)
                    .findFirst()
                    .orElse(null);
            if (series != null) {
                return new ResponseEntity<>(series, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Contenido no encontrado", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> returnContent(int userId) {
        LibraryEntity libraryRepo = libraryRepository.findByUserId(userId);
        Map<String, Object> rentMap = new HashMap<>();
        List<RentEntity> rents = rentRepository.findAll()
                .stream()
                .filter(r -> r.getUser().getId() == userId)
                .toList();

        if (rents.size() > 0) {
            List<SeriesEntity> seriesList = libraryRepo.getSeries();
            List<MovieEntity> moviesList = libraryRepo.getMovies();

            if (seriesList.size() > 0) {
                for (SeriesEntity series : seriesList) {
                    for (RentEntity rentEntity : rents) {
                        if (rentEntity.getContent().getId() == series.getId()) {
                            rentMap.put(series.getName(), rentEntity);
                        }
                    }
                }
            }
            if (moviesList.size() > 0) {
                for (MovieEntity movie : moviesList) {
                    for (RentEntity rentEntity : rents) {
                        if (rentEntity.getContent().getId() == movie.getId()) {
                            rentMap.put(movie.getName(), rentEntity);
                        }
                    }
                }
            }
        }
        return new ResponseEntity<>(rentMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> removeContent(int userId, int contentId) {
        LibraryEntity libraryRepo = libraryRepository.findByUserId(userId);
        ContentEntity content = libraryRepo.getMovies().stream()
                .filter(m -> m.getId() == contentId)
                .findFirst()
                .orElse(null);
        if (content != null) {
            libraryRepo.getMovies().remove(content);
            return new ResponseEntity<>(libraryRepository.save(libraryRepo), HttpStatus.OK);
        }

        content = libraryRepo.getSeries().stream()
                .filter(s -> s.getId() == contentId)
                .findFirst()
                .orElse(null);
        if (content != null) {
            libraryRepo.getSeries().remove(content);
            return new ResponseEntity<>(libraryRepository.save(libraryRepo), HttpStatus.OK);
        }
        
        return new ResponseEntity<>("No se encontró contenido", HttpStatus.NOT_FOUND);
    }
}
