package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "library_movies", 
        joinColumns = @JoinColumn(name = "library_id"), 
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<MovieEntity> movies = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "library_series", 
        joinColumns = @JoinColumn(name = "library_id"), 
        inverseJoinColumns = @JoinColumn(name = "series_id")
    )
    private List<SeriesEntity> series = new ArrayList<>();

    public LibraryEntity(int id, UserEntity user, ArrayList<MovieEntity> movies, ArrayList<SeriesEntity> series) {
        this.id = id;
        this.user = user;
        this.movies = movies;
        this.series = series;
    }

    public LibraryEntity() {
    }

    @Override
    public String toString() {
        return this.id + ": " + this.user +
                ", " + this.movies.toString() + " - "
                + this.series.toString();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<MovieEntity> getMovies() {
        return this.movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    public List<SeriesEntity> getSeries() {
        return this.series;
    }

    public void setSeries(List<SeriesEntity> series) {
        this.series = series;
    }

}
