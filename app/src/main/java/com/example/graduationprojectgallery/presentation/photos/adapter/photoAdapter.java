package com.example.graduationprojectgallery.presentation.photos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.presentation.photos.photoModel;

import java.util.List;

public class photoAdapter  extends RecyclerView.Adapter<photoAdapter.PhotoViewHolder>{

    private List<photoModel> photosPathList;

    public  photoAdapter(List<photoModel> photoPathList)
    {
        this.photosPathList=photoPathList;
    }
    @NonNull
    @Override
    public photoAdapter.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photos_fragment_recycler_item,parent, false);
        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull photoAdapter.PhotoViewHolder holder, int position) {
        photoModel photoModel = photosPathList.get(position);
        holder.img.setImageURI(photoModel.getUri());

    }

    @Override
    public int getItemCount() {
        return photosPathList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView img ;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.photos_fragment_image);


        }


    }
}
