package com.example.graduationprojectgallery.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.activities.adapter.ImageSliderPagerAdapter;

public class PhotosViewActivity extends AppCompatActivity {

    private ViewPager viewPager;
    int position ;
    ImageSliderPagerAdapter imageSliderPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_view);

        viewPager=findViewById(R.id.slideShow);


        imageSliderPagerAdapter = new ImageSliderPagerAdapter(this );

        viewPager.setAdapter(imageSliderPagerAdapter);


    }



}
