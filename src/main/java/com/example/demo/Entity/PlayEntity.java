package com.example.demo.Entity;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlayEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private ContentEntity content;
    private LocalDateTime date;
    private Duration duration;
    private int rate;

    public PlayEntity(){}

    public PlayEntity(int id, UserEntity user, ContentEntity content, LocalDateTime date, Duration duration, int rate){
        this.id = id;
        this.user = user;
        this.content = content;
        this.date = date;
        this.duration = duration;
        this.rate = rate;
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

    public LocalDateTime getDate(){
        return this.date;
    }
    public void setDate(LocalDateTime date){
        this.date = date;
    }

    public Duration getDuration(){
        return this.duration;
    }
    public void setDuration(Duration duration){
        this.duration = duration;
    }

    public int getRate(){
        return this.rate;
    }
    public void setRate(int rate){
        this.rate = rate;
    }

    @Override
    public String toString(){
        return this.id + " " + this.user.getName() + 
        " " + this.content.getName() + " " + this.date.toString() + 
        " " + this.duration + " " + this.rate;
    }
}
