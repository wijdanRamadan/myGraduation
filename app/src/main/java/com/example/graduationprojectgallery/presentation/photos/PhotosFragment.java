package com.example.graduationprojectgallery.presentation.photos;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.activities.PhotosViewActivity;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.example.graduationprojectgallery.presentation.photos.adapter.PhotosFragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.fragment.NavHostFragment.findNavController;
import static com.example.graduationprojectgallery.activities.MainActivity.urls;


public class PhotosFragment extends BaseFragment implements PhotosFragmentAdapter.PhotoClickListener {



    private PhotosFragmentAdapter mAdapter;
    private BottomNavigationView bottomNavigationView;

    private PhotosFragmentAdapter.PhotoClickListener photoClickListener;
    private RecyclerView recyclerView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhotosFragment() {
    }


    public static PhotosFragment newInstance(String param1, String param2) {

        PhotosFragment fragment = new PhotosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photos, container, false);


        return view;
    }

    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void setNav() {
        BottomNavigationItemView photos = getActivity().findViewById(R.id.photos);
        BottomNavigationItemView albums = getActivity().findViewById(R.id.albums);
        BottomNavigationItemView search = getActivity().findViewById(R.id.search);

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId() != R.id.photosFragment) {
                }
            }
        });





        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId() != R.id.searchFragment) {

                    findNavigationController().navigate(R.id.action_photosFragment_to_searchFragment2);
                }
            }
        });
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findNavController(PhotosFragment.this).getCurrentDestination().getId()!=R.id.albumsFragment)
                {
                    findNavigationController().navigate(R.id.action_photosFragment_to_albumsFragment);
                }
            }
        });

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new PhotosFragmentAdapter(getActivity(), urls);
        mAdapter.setPhotoClickListener(this);

        recyclerView = view.findViewById(R.id.photos_recycler_view);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));


    }


    @Override
    public void OnPhotoClick(int position ) {
        Intent intent = new Intent(getContext() , PhotosViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView.setVisibility(View.VISIBLE);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();


    }
}
