package com.example.graduationprojectgallery.activities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.models.PhotoModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;



public class MainActivity extends AppCompatActivity
{
    public static List<String> urls;
    public static List<PhotoModel> photos;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        urls = HelperClass.getImagePaths(this);

        photos=HelperClass.getPhotos(this);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);


    }



}

