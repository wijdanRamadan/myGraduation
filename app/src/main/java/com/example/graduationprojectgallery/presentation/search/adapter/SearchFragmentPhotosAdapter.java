package com.example.graduationprojectgallery.presentation.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.helperClasses.FirebaseFunctions;
import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.models.PhotoModel;
import com.example.graduationprojectgallery.presentation.photos.adapter.PhotosFragmentPhotosAdapter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragmentPhotosAdapter  extends RecyclerView.Adapter<SearchFragmentPhotosAdapter.SearchFragmentViewHolder> {

    PhotosFragmentPhotosAdapter.PhotoClickListener photoClickListener;
    private Context mContext;
    private List<PhotoModel> photoModelList = new ArrayList<>();


    public SearchFragmentPhotosAdapter(Context context , List<PhotoModel> photoModelList) {
        mContext = context;
        this.photoModelList=photoModelList;

    }

    public void setPhotoModelList (List <PhotoModel> photoModelList)

    {
        this.photoModelList=photoModelList;
    }

    public PhotosFragmentPhotosAdapter.PhotoClickListener getPhotoClickListener() {
        if (photoClickListener == null) {
            photoClickListener = new PhotosFragmentPhotosAdapter.PhotoClickListener() {
                @Override
                public void OnPhotoClick(int position) {
                    Toast.makeText(mContext, FirebaseFunctions.testFunction(photoModelList.get(position),mContext), Toast.LENGTH_LONG).show();

                }

            };
        }
        return photoClickListener;
    }



    @NonNull
    @Override
    public SearchFragmentPhotosAdapter.SearchFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photos_fragment_recycler_item, parent, false);
        return new SearchFragmentPhotosAdapter.SearchFragmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchFragmentViewHolder holder, int position) {
        if (photoModelList != null) {

            PhotoModel photo = photoModelList.get(position);
            holder.Bind(photo, position);
        }
    }



    @Override
    public int getItemCount() {
        return photoModelList.size();
    }


    public class SearchFragmentViewHolder extends RecyclerView.ViewHolder {

        public ImageView searchFragmentImageView;
        public TextView imageDate;

        public SearchFragmentViewHolder(@NonNull View itemView) {
            super(itemView);

            searchFragmentImageView = itemView.findViewById(R.id.photos_fragment_recycler_image);
        }

        public void Bind(final String photoPath, final int position) {
            HelperClass.show(photoPath, mContext, searchFragmentImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPhotoClickListener().OnPhotoClick(position);
                }
            });
        }

        public void Bind(final PhotoModel photoModel, final int position) {
            HelperClass.show(photoModel, mContext, searchFragmentImageView);

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

