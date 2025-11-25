package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RentEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private ContentEntity content;
    private LocalDate rentDate;
    private LocalDate expirationDate;
    private double finalPrice;

    public RentEntity(int id, UserEntity user, ContentEntity content, LocalDate rentDate, LocalDate expirationDate, double finalPrice){
        this.id = id;
        this.user = user;
        this.content = content;
        this.rentDate = rentDate;
        this.expirationDate = expirationDate;
        this.finalPrice = finalPrice;
    }

    public RentEntity(){}

    @Override
    public String toString(){
        return this.id + ": " + this.user.getName() + 
        ", " + this.content.getName() + ", " + 
        this.expirationDate + ", " + this.finalPrice; 
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

    public ContentEntity getContent(){
        return this.content;
    }
    public void setContent(ContentEntity content){
        this.content = content;
    }

    public LocalDate getRentDate(){
        return this.rentDate;
    }
    public void setRentDate(LocalDate rentDate){
        this.rentDate = rentDate;
    }

    public LocalDate getExpirationDate(){
        return this.expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate){
        this.expirationDate = expirationDate;
    }

    public double getFinalPrice(){
        return this.finalPrice;
    }
    public void setFinalPrice(double finalPrice){
        this.finalPrice = finalPrice;
    }
}
