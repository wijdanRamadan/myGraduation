package com.example.graduationprojectgallery.presentation.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.presentation.search.test.SearchFragmentRecyclerView;

public class SearchFragmentObservablePhotosAdapter extends
        SearchFragmentRecyclerView.Adapter<SearchFragmentObservablePhotosAdapter.SearchFragmentViewHolder>{

    private Context mContext;

    private ObservableList<String> photoStringObservableList ;

    public SearchFragmentObservablePhotosAdapter (Context context , ObservableList<String> list) {
        this.photoStringObservableList=list;
        this.mContext=context;
    }

    @NonNull
    @Override
    public SearchFragmentObservablePhotosAdapter.SearchFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_fragment_recycler_item, parent, false);
        return new SearchFragmentObservablePhotosAdapter.SearchFragmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchFragmentObservablePhotosAdapter.SearchFragmentViewHolder holder, final int position) {

 photoStringObservableList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<String>>() {
     @Override
     public void onChanged(ObservableList<String> sender) {

         String photoPath = sender.get(position);
         holder.Bind(photoPath,position);
     }

     @Override
     public void onItemRangeChanged(ObservableList<String> sender, int positionStart, int itemCount) {

     }

     @Override
     public void onItemRangeInserted(ObservableList<String> sender, int positionStart, int itemCount) {

     }

     @Override
     public void onItemRangeMoved(ObservableList<String> sender, int fromPosition, int toPosition, int itemCount) {

     }

     @Override
     public void onItemRangeRemoved(ObservableList<String> sender, int positionStart, int itemCount) {

     }
 });


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class SearchFragmentViewHolder extends RecyclerView.ViewHolder {

        public ImageView searchFragmentImageView;
        public SearchFragmentViewHolder(@NonNull View itemView) {
            super(itemView);
            searchFragmentImageView = itemView.findViewById(R.id.photos_fragment_recycler_image);
        }

        public void Bind(String photoPath, int position) {

            HelperClass.show(photoPath, mContext, searchFragmentImageView);
        }
    }
}
