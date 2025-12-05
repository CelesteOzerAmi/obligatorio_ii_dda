package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PlayEntity;

public interface PlayRepository extends JpaRepository<PlayEntity, Integer> {
    public ArrayList<PlayEntity> findAll();

    public ArrayList<PlayEntity> findAllByUserId(int userId);
}
