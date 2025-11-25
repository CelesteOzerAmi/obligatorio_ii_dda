package com.example.demo.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SERIES")
public class SeriesEntity extends ContentEntity{
    
    private int seasons;
    private int chapters;

    public SeriesEntity(int id, String name, String description, CategoryEntity category, 
        int seasons, int chapters, String year, double purchasePrice, double rentPrice, boolean premiumExclusive){
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.seasons = seasons;
        this.chapters = chapters;
        this.year = year;
        this.purchasePrice = purchasePrice;
        this.rentPrice = rentPrice;
        this.premiumExclusive = premiumExclusive;
    }

    public SeriesEntity(){}

    @Override
    public String toString(){
        return this.id + ": " + this.name + ", " +
        this.description + ", cat. " + this.category +
        ", seasons: " + this.seasons + ", chapters: " +
        this.chapters + " year: " + this.year + ". purchase $: " + this.purchasePrice 
        + ", rent $: " + this.rentPrice + 
        (premiumExclusive ? ", premiumExclusive." : ", not premiumExclusive.");}

    
    public int getSeasons(){
        return this.seasons;
    }
    public void setSeasons(int seasons){
        this.seasons = seasons;
    }

    public int getChapters(){
        return this.chapters;
    }
    public void setChapters(int chapters){
        this.chapters = chapters;
    }
}
