package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.LibraryEntity;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Integer> {
    LibraryEntity findByUserId(int userId);
}
