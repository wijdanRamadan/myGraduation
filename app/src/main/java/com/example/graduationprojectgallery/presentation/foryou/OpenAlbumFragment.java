package com.example.graduationprojectgallery.presentation.foryou;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.activities.PhotosViewActivity;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.models.PhotoModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.fragment.NavHostFragment.findNavController;
import static com.example.graduationprojectgallery.activities.MainActivity.photos;

public class OpenAlbumFragment extends BaseFragment implements OpenAlbumAdapter.PhotoClickListener {

    GridLayoutManager layoutManager;
    public OpenAlbumAdapter mAdapter;
    private RecyclerView recyclerView;
    private View view;
    TextView album_name_header;
    TextView albums_back_button;
    ImageView back_button;
    Toolbar toolbar;

    private OpenAlbumAdapter.PhotoClickListener photoClickListener;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BuildRecyclerView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_open_album, container, false);
        album_name_header = view.findViewById(R.id.album_name_header_textview);
        album_name_header.setText(getArguments().getString("album_name"));

        back_button = view.findViewById(R.id.back_to_albums_imageView);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(OpenAlbumFragment.this).popBackStack();
            }
        });

        albums_back_button = view.findViewById(R.id.back_to_albums_textview);
        albums_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(OpenAlbumFragment.this).popBackStack();
            }
        });


        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(false); // if menu added, this needs to be true

        BottomNavigationView navigationView = getActivity().findViewById(R.id.bottom_nav); //hides bottom navigation menu
        navigationView.setVisibility(View.GONE);
        toolbar = getActivity().findViewById(R.id.app_toolbar);
    }

    public void BuildRecyclerView() {

        ArrayAdapter<String> detailesAdapter = new ArrayAdapter<String>(getActivity()
                , android.R.layout.simple_list_item_1
        );
        detailesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        HelperClass.getAlbumImages(getArguments().getString("album_name"), getActivity());
        mAdapter = new OpenAlbumAdapter(getActivity(), getArguments().getString("album_name"), HelperClass.album_images_uri);
        mAdapter.setPhotoClickListener(this);
        recyclerView = view.findViewById(R.id.open_album_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void OnPhotoClick(int position) {

        for (PhotoModel p : photos) {
            String bug = (HelperClass.album_images_uri.get(position).getPath()).toString();
            if (p.getPath() != null && p.getPath().contains(bug)) {
                Intent intent = new Intent(getContext(), PhotosViewActivity.class);
                intent.putExtra("photo", p); //tazzy do NOT change "photo" !!!!!!!!!
                startActivity(intent);
                break;
            }
        }
//        PhotoModel photoModel = HelperClass.getPhotos(getContext(), HelperClass.album_images_uri, position);
//        PhotoModel photoModel2 = new PhotoModel(photoModel.getPath(), photoModel.getDate(), photoModel.getTitle(), photoModel.getSize());
//        Intent intent = new Intent(getContext(), PhotosViewActivity.class);
//        intent.putExtra("photo", photoModel2);
//        startActivity(intent);

    }

}
