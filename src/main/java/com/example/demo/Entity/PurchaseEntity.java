package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PurchaseEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private ContentEntity content;
    private LocalDate purchaseDate;
    private double finalPrice;

    public PurchaseEntity(int id, UserEntity user, ContentEntity content, LocalDate purchaseDate, double finalPrice){
        this.id = id;
        this.user = user;
        this.content = content;
        this.purchaseDate = purchaseDate;
        this.finalPrice = finalPrice;
    }

    public PurchaseEntity(){}

    @Override
    public String toString(){
        return this.id + ": " + this.user.getName() + 
        ", " + this.content.getName() + ", " + 
        this.purchaseDate + ", " + this.finalPrice; 
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

    public LocalDate getPurchaseDate(){
        return this.purchaseDate;
    }
    public void setPurchaseDate(LocalDate purchaseDate){
        this.purchaseDate = purchaseDate;
    }

    public double getFinalPrice(){
        return this.finalPrice;
    }
    public void setFinalPrice(double finalPrice){
        this.finalPrice = finalPrice;
    }
}
