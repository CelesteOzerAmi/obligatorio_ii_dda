package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CategoryEntity {
    
    @Id
    private String name;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public CategoryEntity(String name){
        this.name = name;
    }

    public CategoryEntity(){}

    @Override
    public String toString(){
        return this.name;
    }
}
