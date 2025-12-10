package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserRequest;
import com.example.demo.Entity.LibraryEntity;
import com.example.demo.Entity.SubscriptionEntity;
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
    public ResponseEntity<?> postUser(UserRequest userRequest) {
        if (userRequest.name() != null || userRequest.email() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(userRequest.name());
            userEntity.setEmail(userRequest.email());
            userEntity.setSignInDate(LocalDate.now());
            userRepository.save(userEntity);

            LibraryEntity library = new LibraryEntity();
            library.setUser(userEntity);
            libraryRepository.save(library);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error al registrar usuario", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> updateUser(int id, UserRequest UserRequest) {

        if (UserRequest.name() != null && UserRequest.email() != null) {
            Optional<UserEntity> userRepos = userRepository.findById(id);

            if (!userRepos.isPresent()) {
                return new ResponseEntity<>("usuario no existe", HttpStatus.NOT_FOUND);
            }

            UserEntity userFound = userRepos.get();
            userFound.setName(UserRequest.name());
            userFound.setEmail(UserRequest.email());

            return new ResponseEntity<>(userRepository.save(userFound), HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al actualizar datos", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        Optional<UserEntity> userRepos = userRepository.findById(id);

        if (!userRepos.isPresent()) {
            return new ResponseEntity<>("usuario no existe", HttpStatus.NOT_FOUND);
        }

        List<SubscriptionEntity> subscriptions = subscriptionRepository.findAll();
        for (SubscriptionEntity subs : subscriptions) {
            if (subs.getUser().getId() == id) {
                subscriptionRepository.delete(subs);
            }
        }
        userRepository.delete(userRepos.get());
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }

}
