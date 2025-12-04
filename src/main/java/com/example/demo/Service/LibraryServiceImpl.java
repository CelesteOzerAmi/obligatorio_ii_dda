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
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.ContentRepository;
import com.example.demo.Repository.LibraryRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    public LibraryRepository libraryRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

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
}
