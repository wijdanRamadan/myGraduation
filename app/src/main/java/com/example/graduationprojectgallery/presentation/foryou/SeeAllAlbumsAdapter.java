package com.example.graduationprojectgallery.presentation.foryou;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.models.Album;

import java.util.ArrayList;


public class SeeAllAlbumsAdapter extends RecyclerView.Adapter<SeeAllAlbumsAdapter.ViewHolder> implements SeeAllAlbumsFragment.OnMinusClicked {


    private static final String TAG = "SeeAllAlbumsAdapter";

    //vars
    private ArrayList<Album> mAlbums = new ArrayList<>();
    public static SeeAllAlbumsAdapter.RecyclerViewClickListener itemListener;
    private Context mContext;
    public Fragment f;
    public FragmentManager fm;
    public static ArrayList<ImageView> minus_button;


    public void showMinusIcon() {
        SeeAllAlbumsFragment.first_click = true;
//      for(int position = 0; position <minus_button.size(); position++){
//        this.minus_button.get(position).animate().alpha(1.0f);
//        this.minus_button.get(position).setVisibility(View.VISIBLE);
//        this.notifyDataSetChanged();
//      }
        // Toast.makeText(mContext, "" + first_click, Toast.LENGTH_SHORT).show();
    }

    public void hideMinusIcon() {
//        for(int position = 0; position <minus_button.size(); position++) {
//            this.minus_button.get(position).animate().alpha(0.1f);
//            this.minus_button.get(position).setVisibility(View.GONE);
//
//            this.notifyDataSetChanged();
//        }
        SeeAllAlbumsFragment.first_click = false;

    }


    public void InitiateDeleteAlbumDialog(View v, int position) {

    }

    public SeeAllAlbumsAdapter(Context mContext, ArrayList<Album> mAlbums, Fragment f, FragmentManager fm) {

        this.mContext = mContext;
        this.mAlbums = mAlbums;
        this.itemListener = itemListener;
        this.fm = fm;
        this.f = f;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_albums_recycler_item, parent, false);

        return new SeeAllAlbumsAdapter.ViewHolder(view);
    }


    //TODO get mImageUrl from the actuall albums and fix the onclicklistener

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setTag(mAlbums.get(position));
        holder.album_name.setText(mAlbums.get(position).getName());
        //minus_button.add(holder.return_minus_icon());
        holder.album_thumbnail.setImageURI(mAlbums.get(position).getThumbnail_path());

        if (SeeAllAlbumsFragment.first_click) {
            Toast.makeText(mContext, mAlbums.get(position).getName() + "delete mode", Toast.LENGTH_SHORT).show();

        } else {
            holder.delete_icon.setVisibility(View.GONE);
        }

        holder.album_thumbnail.setOnClickListener(new View.OnClickListener() {  // tazzy when clicking on an album thumbnail
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image : " + mAlbums.get(position).getName());
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                NavController nav = Navigation.findNavController(view);
                if (!SeeAllAlbumsFragment.first_click) {
                    if (mAlbums.get(position).getName() == "Recent") {
                        nav.navigate(R.id.action_seeAllAlbumsFragment_to_photosFragment);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("album_name", mAlbums.get(position).getName());
                        nav.navigate(R.id.action_seeAllAlbumsFragment_to_openAlbumFragment, bundle);
                    }
                    Toast.makeText(mContext, mAlbums.get(position).getName() + "", Toast.LENGTH_SHORT).show();
                } else {

                    final EditAlbumsDialog dialog = new EditAlbumsDialog(mAlbums.get(position).getName(), mContext, f, position);
                    dialog.setTargetFragment(f, 0);
                    dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                    dialog.show(fm, "EditAlbumsDialog");
                    Toast.makeText(mContext, "EDIT MODE", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView album_thumbnail;
        public TextView album_name;
        public ImageView delete_icon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            album_name = itemView.findViewById(R.id.see_all_album_name_textView);
            album_thumbnail = itemView.findViewById(R.id.see_all_album_thumbnail_imageView);
            delete_icon = itemView.findViewById(R.id.minus_albums);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }

    }

    public interface RecyclerViewClickListener {

        void onClick(int position);
    }


}
