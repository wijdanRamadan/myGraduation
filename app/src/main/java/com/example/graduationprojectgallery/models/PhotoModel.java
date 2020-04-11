package com.example.graduationprojectgallery.models;

import android.widget.Spinner;

import androidx.room.Entity;

import java.io.Serializable;


@Entity
public class PhotoModel implements Serializable {

    private String path;
    private String date;
    private String title;
    private String size;
    private String latitude;


    public PhotoModel(String path, String date, String title, String size) {
        this.path = path;
        this.date = date;
        this.title = title;
        this.size=size;

    }

    public PhotoModel() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
