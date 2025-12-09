package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private LocalDate signInDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private LibraryEntity library;

    public UserEntity(int id, String name, String email, LocalDate signInDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.signInDate = LocalDate.now();
    }

    public UserEntity() {
    }

    @Override
    public String toString() {
        return this.id + ": " + this.name +
                ", " + this.email + ", " + this.signInDate +
                ".";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getSignInDate() {
        return this.signInDate;
    }

    public void setSignInDate(LocalDate signInDate) {
        this.signInDate = signInDate;
    }
}
