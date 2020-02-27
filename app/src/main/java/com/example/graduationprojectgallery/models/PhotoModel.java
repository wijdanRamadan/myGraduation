package com.example.graduationprojectgallery.models;

import androidx.room.Entity;


@Entity
public class PhotoModel {

        public PhotoModel(String path, String date, String title) {
                this.path = path;
                this.date = date;
                this.title = title;

        }


        public String path;

        public PhotoModel() {
        }

        public String getPath() {
                return path;
        }

        public void setPath(String path) {
                this.path =path;
        }


        public String date;

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public String title;

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }






}
