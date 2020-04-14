package com.example.graduationprojectgallery.models;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;


import java.io.File;
import java.io.Serializable;


public class PhotoModel implements Serializable {

    private String path;
    private String date;
    private String title;
    private String size;
    private Bitmap bitmap;


    public PhotoModel(String path, String date, String title, String size ) {
        this.path = path;
        this.date = date;
        this.title = title;
        this.size=size;



    }


    public String getPath() {
        return path;
    }


    public String getDate() {
        return date;
    }


    public String getTitle() {
        return title;
    }

    public String getSize() {
        return size;
    }
    public Bitmap getBitmap()
    {
        return bitmap;
    }

}
