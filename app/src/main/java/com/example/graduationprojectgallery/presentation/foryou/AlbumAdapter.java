package com.example.graduationprojectgallery.presentation.foryou;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.graduationprojectgallery.R;

import java.util.ArrayList;


//tazzy this :https://www.youtube.com/watch?v=94rCjYxvzEE


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {


    private static final String TAG = "AlbumAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext ;

    public AlbumAdapter(Context mContext, ArrayList<String> mNames, ArrayList<String> mImageUrls) {
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //tazzy inflating the recycler item layout


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foryou_fragment_recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) { // tazzy binding items to albums thumbnails
        Log.d(TAG, "onCreateViewHolder: called. ");
        Glide.with(mContext).asBitmap().load(mImageUrls.get(position)).into(holder.album_thumbnail); // tazzy taking imageUrls & assigning them to album thumbnails
        holder.album_name.setText(mNames.get(position));

        holder.album_thumbnail.setOnClickListener(new View.OnClickListener() {  // tazzy when clicking on an album thumbnail
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image : " + mNames.get(position));
                Toast.makeText(mContext, mNames.get(position) + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView album_thumbnail;
        TextView album_name;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            album_thumbnail = itemView.findViewById(R.id.album_thumbnail_imageView);
            album_name = itemView.findViewById(R.id.album_name_textView);

        }

    }
}
