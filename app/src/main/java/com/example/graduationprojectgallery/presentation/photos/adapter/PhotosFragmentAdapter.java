package com.example.graduationprojectgallery.presentation.photos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.HelperClass;
import com.example.graduationprojectgallery.R;

import java.util.List;

import static com.example.graduationprojectgallery.R.id.photos_fragment_recycler_image;


public class PhotosFragmentAdapter extends RecyclerView.Adapter<PhotosFragmentAdapter.PhotosFragmentViewHolder>  {

    private List<String> photos;
    private Context mContext;
    private int globalPosition;
    private  int x ;

    public PhotoClickListener getPhotoClickListener() {
        if (photoClickListener==null)
        {
            photoClickListener = new PhotoClickListener() {
                @Override
                public void OnPhotoClick(int position) {
                    Toast.makeText(mContext , "null" ,Toast.LENGTH_LONG).show();

                }

            };
        }
        return photoClickListener;
    }

    public void setPhotoClickListener(PhotoClickListener photoClickListener) {
        this.photoClickListener = photoClickListener;
    }

    PhotoClickListener photoClickListener;



    public PhotosFragmentAdapter(Context context, List<String> photos  ) {
        mContext = context;
        this.photos = photos;
    }


    @NonNull
    @Override
    public PhotosFragmentAdapter.PhotosFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photos_fragment_recycler_item, parent, false);
        return new PhotosFragmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosFragmentAdapter.PhotosFragmentViewHolder holder, int position) {

        if (photos != null) {
            globalPosition=position;
            String photo = photos.get(position);
            holder.Bind(photo);


        }


    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class PhotosFragmentViewHolder extends RecyclerView.ViewHolder {

        public ImageView photosFragmentImageView;

        public PhotosFragmentViewHolder(@NonNull View itemView) {
            super(itemView);

            photosFragmentImageView = itemView.findViewById(photos_fragment_recycler_image);

        }
        public  void Bind(final String photoPath)
        {
            HelperClass.show(photoPath , mContext ,photosFragmentImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   getPhotoClickListener().OnPhotoClick(globalPosition);
                }
            });
        }

    }

    public interface PhotoClickListener {
        void OnPhotoClick(int position);

    }
}
