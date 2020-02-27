package com.example.graduationprojectgallery.presentation.photos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.models.PhotoModel;

import java.util.List;

import static com.example.graduationprojectgallery.R.id.photos_fragment_recycler_image;
import static com.example.graduationprojectgallery.activities.MainActivity.photos;
import static com.example.graduationprojectgallery.activities.MainActivity.urls;


public class PhotosFragmentAdapter extends RecyclerView.Adapter<PhotosFragmentAdapter.PhotosFragmentViewHolder>  {

    private Context mContext;
    TextView date;
    private List<PhotoModel> photoModelList;



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



    public PhotosFragmentAdapter(Context context  ) {
        mContext = context;

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

        if (urls != null) {
          //  String photo = urls.get(position);
            PhotoModel photo = photos.get(position);
            holder.Bind(photo , position);
        }

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class PhotosFragmentViewHolder extends RecyclerView.ViewHolder {

        public ImageView photosFragmentImageView;
        public TextView imageDate;

        public PhotosFragmentViewHolder(@NonNull View itemView) {
            super(itemView);

            photosFragmentImageView = itemView.findViewById(photos_fragment_recycler_image);
            imageDate=itemView.findViewById(R.id.date_header);

        }
        public  void Bind(final String photoPath , final int position)
        {
            HelperClass.show(photoPath, mContext ,photosFragmentImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   getPhotoClickListener().OnPhotoClick(position);
                }
            });
        }

        public void Bind(final PhotoModel photoModel ,final int position)
        {

            HelperClass.show(photoModel ,mContext,photosFragmentImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPhotoClickListener().OnPhotoClick(position);
                }
            });


        }

    }

    public interface PhotoClickListener {
        void OnPhotoClick(int position);

    }
}
