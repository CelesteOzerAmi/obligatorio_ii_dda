package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SubscriptionEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    private LocalDate activationDate;
    private double premiumDiscount = 0.80;
    private boolean active;

    public SubscriptionEntity(int id, UserEntity user, LocalDate activationDate, double premiumDiscount, boolean active){
        this.id = id;
        this.user = user;
        this.activationDate = activationDate;
        this.premiumDiscount = premiumDiscount;
        this.active = active;
    }

    public SubscriptionEntity(){}

    @Override
    public String toString(){
        return this.id + ": " + this.user.getName() + ", "
        + this.activationDate + "."; 
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

    public LocalDate getActivationDate(){
        return this.activationDate;
    }
    public void setActivationDate(LocalDate activationDate){
        this.activationDate = activationDate;
    }

    public double getPremiumDiscount(){
        return this.premiumDiscount;
    }
    public void setPremiumDiscount(double premiumDiscount){
        this.premiumDiscount = premiumDiscount;
    }

    public boolean isActive(){
        return this.active;
    }
    public void setActive(boolean active){
        this.active = active;
    }
}
