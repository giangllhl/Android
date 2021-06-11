package com.ptit.example.projectfinal.model;

import java.io.Serializable;

public class Song implements Serializable {
    String id;
    String image;
    String title;
    String singer;
    String favorite;

    public Song(String image, String title, String singer, String favorite) {
        this.image = image;
        this.title = title;
        this.singer = singer;
        this.favorite = favorite;
    }

    public Song(String id, String image, String title, String singer, String favorite) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.singer = singer;
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
