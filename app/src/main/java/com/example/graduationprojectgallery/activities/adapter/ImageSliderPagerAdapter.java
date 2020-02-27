/*package com.example.graduationprojectgallery.activities.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.graduationprojectgallery.R;
import com.github.chrisbanes.photoview.PhotoView;


import static com.example.graduationprojectgallery.activities.MainActivity.photos;


public class ImageSliderPagerAdapter extends PagerAdapter
{
    private LayoutInflater layoutInflater;
    private Context context;
    int photoPosition;


    public ImageSliderPagerAdapter(Context context , int photoPosition)
    {
        this.context=context;
        this.photoPosition=photoPosition;

    }


    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
       return (view == (LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

      layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      View view = layoutInflater.inflate(R.layout.swipe, container ,false);

      PhotoView photoView =  view.findViewById(R.id.photoViewTest);
      if(photos.get(photoPosition).getPath()!=null) {
          Glide
                  .with(context)
                  .load(photos.get(photoPosition).getPath())
                  .diskCacheStrategy(DiskCacheStrategy.ALL)
                  .into(photoView);
      }

    container.addView(view);

      return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}
*/