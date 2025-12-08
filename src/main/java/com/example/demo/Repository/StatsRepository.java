package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.StatsEntity;

public interface StatsRepository extends JpaRepository<StatsEntity, Integer> {
    public ArrayList<StatsEntity> findAll();

    public ArrayList<StatsEntity> findAllByUserId(int userId);
}
