package com.example.demo.Entity;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class HistoryEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "history_movies",
        joinColumns = @JoinColumn(name = "history_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private ArrayList<MovieEntity> movies = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "history_series",
        joinColumns = @JoinColumn(name = "history_id"),
        inverseJoinColumns = @JoinColumn(name = "series_id")
    )
    private ArrayList<SeriesEntity> series = new ArrayList<>();

    public HistoryEntity(int id, UserEntity user, ArrayList<MovieEntity> movies, ArrayList<SeriesEntity> series){
        this.id = id;
        this.user = user;
        this.movies = movies;
        this.series = series;
    }

    public HistoryEntity(){}

    @Override
    public String toString(){
        return this.id + ": " + this.user + 
        ", " + this.movies.toString() + " - " 
        + this.series.toString();
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public UserEntity getUser(){
        return this.user;
    }
    public void setUser(UserEntity user){
        this.user = user;
    }

    public ArrayList<MovieEntity> getMovies(){
        return this.movies;
    }
    public void setMovies(ArrayList<MovieEntity> movies){
        this.movies = movies;
    }

    public ArrayList<SeriesEntity> getseries(){
        return this.series;
    }
    public void setSeries(ArrayList<SeriesEntity> series){
        this.series = series;
    }
    
}
