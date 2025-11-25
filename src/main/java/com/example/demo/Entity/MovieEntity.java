package com.example.demo.Entity;

import java.time.Duration;

import jakarta.persistence.Entity;

@Entity
public class MovieEntity extends ContentEntity{
    
    private Duration duration;

    public MovieEntity(int id, String name, String description, CategoryEntity category, Duration duration,
        String year, double purchasePrice, double rentPrice, boolean premiumExclusive){
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.duration = duration;
        this.year = year;
        this.purchasePrice = purchasePrice;
        this.rentPrice = rentPrice;
        this.premiumExclusive = premiumExclusive;

    }

    public MovieEntity(){}

    @Override
    public String toString(){
        return this.id + ": " + this.name + ", " +
        this.description + ", cat. " + this.category +
        " year: " + this.year + ". purchase $: " + this.purchasePrice 
        + ", rent $: " + this.rentPrice + 
        (premiumExclusive ? ", premiumExclusive." : ", not premiumExclusive.");
    }

    public Duration getDuration(){
        return this.duration;
    }
    public void setDuration(Duration duration){
        this.duration = duration;
    }
}
