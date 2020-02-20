package com.example.graduationprojectgallery.presentation.photos;

import android.net.Uri;


public class photoModel {

        public Uri getUri() {
                return uri;
        }

        public void setUri(Uri uri) {
                this.uri = uri;
        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public Uri uri;
        public String date;

}
