package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.LibraryEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.LibraryRepository;
import com.example.demo.Repository.SubscriptionRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public LibraryRepository libraryRepository;

    @Autowired
    public SubscriptionRepository subscriptionRepository;

    static ArrayList<UserEntity> usersList = new ArrayList<>();

    @Override
    public ResponseEntity<ArrayList<UserEntity>> getAll() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @Override
    public ResponseEntity<ArrayList<UserEntity>> getPremiumUsers() {
        ArrayList<UserEntity> all = userRepository.findAll();

        ArrayList<UserEntity> premiumUsers = new ArrayList<>();
        for (UserEntity userEntity : all) {
            if (subscriptionRepository.findAll().stream()
                    .filter(s -> s.isActive() && s.getUser().equals(userEntity))
                    .findAny().isPresent()) {
                premiumUsers.add(userEntity);
            }
        }

        return ResponseEntity.ok().body(premiumUsers);
    }

    @Override
    public ResponseEntity<ArrayList<UserEntity>> getStandardUsers() {
        ArrayList<UserEntity> all = userRepository.findAll();

        ArrayList<UserEntity> standardUsers = new ArrayList<>();

        for (UserEntity userEntity : all) {
            if (!subscriptionRepository.findAll().stream()
                    .filter(s -> s.isActive() && s.getUser().equals(userEntity))
                    .findAny().isPresent()) {
                standardUsers.add(userEntity);
            }
        }

        return ResponseEntity.ok().body(standardUsers);
    }

    @Override
    public ResponseEntity<?> postUser(UserEntity user) {
        if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("sin datos suficientes");
        }
        userRepository.save(user);
        LibraryEntity library = new LibraryEntity();
        library.setUser(user);
        libraryRepository.save(library);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateUser(UserEntity userEntity) {

        Optional<UserEntity> userRepos = userRepository.findById(userEntity.getId());

        if (!userRepos.isPresent()) {
            return new ResponseEntity<>("usuario no existe", HttpStatus.NOT_FOUND);
        }

        if (userEntity.getName().isEmpty() || userEntity.getEmail().isEmpty()
                || userEntity.getSignInDate().equals(null)) {
            return new ResponseEntity<>("faltan datos obligatorios", HttpStatus.BAD_REQUEST);
        }

        UserEntity userFound = userRepos.get();
        userFound.setName(userEntity.getName());
        userFound.setEmail(userEntity.getEmail());
        userFound.setSignInDate(userEntity.getSignInDate());

        return new ResponseEntity<>(userRepository.save(userFound), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        Optional<UserEntity> userRepos = userRepository.findById(id);

        if (!userRepos.isPresent()) {
            return new ResponseEntity<>("usuario no existe", HttpStatus.NOT_FOUND);
        }
        userRepository.delete(userRepos.get());
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }

}
