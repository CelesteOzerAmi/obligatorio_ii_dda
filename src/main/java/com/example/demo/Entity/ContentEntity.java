package com.example.demo.Entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // indica que es clase padre - la estrategia será 
                                                // crear una tabla contenido y una por cada subclase
@DiscriminatorColumn(name = "type")                                                
public abstract class ContentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String name;
    protected String description;

    @ManyToOne
    @JoinColumn(name = "category_name", referencedColumnName = "name")
    protected CategoryEntity category;
    protected String year;
    protected double purchasePrice;
    protected double rentPrice;
    protected boolean premiumExclusive;

    public ContentEntity(int id, String name, String description, CategoryEntity category, 
        String year, double purchasePrice, double rentPrice, boolean premiumExclusive){
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.year = year;
        this.purchasePrice = purchasePrice;
        this.rentPrice = rentPrice;
        this.premiumExclusive = premiumExclusive;
    }
    
    public ContentEntity(){}

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public CategoryEntity getCategory(){
        return this.category;
    }
    public void setCategory(CategoryEntity category){
        this.category = category;
    }

    public String getYear(){
        return this.year;
    }
    public void setYear(String year){
        this.year = year;
    }

    public double getPurchasePrice(){
        return this.purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice){
        this.purchasePrice = purchasePrice;
    }

    public double getRentPrice(){
        return this.rentPrice;
    }
    public void setRentPrice(double rentPrice){
        this.rentPrice = rentPrice;
    }

    public boolean isPremiumExclusive(){
        return this.premiumExclusive;
    }
    public void setPremiumExclusive(boolean premiumExclusive){
        this.premiumExclusive = premiumExclusive;
    }
}
