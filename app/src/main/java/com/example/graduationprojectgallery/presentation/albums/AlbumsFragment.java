package com.example.graduationprojectgallery.presentation.albums;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class AlbumsFragment extends BaseFragment {

    protected NavController findNavigationController() {
        return findNavController(this);
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AlbumsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setNav();

    }
    @Override
    public void setNav()
    {

        BottomNavigationItemView photos = getActivity().findViewById(R.id.photos);
        BottomNavigationItemView foryou = getActivity().findViewById(R.id.foryou);
        BottomNavigationItemView albums = getActivity().findViewById(R.id.albums);
        BottomNavigationItemView search = getActivity().findViewById(R.id.search);
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(AlbumsFragment.this).getCurrentDestination().getId() != R.id.photosFragment) {
                    findNavigationController().navigate(R.id.action_albumsFragment_to_photosFragment);
                }
            }
        });
        // TODO: fix navigation bug :D

        foryou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(AlbumsFragment.this).getCurrentDestination().getId() != R.id.foryouFragment) {

                    findNavigationController().navigate(R.id.action_albumsFragment_to_foryouFragment);
                }
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(AlbumsFragment.this).getCurrentDestination().getId() != R.id.albumsFragment) {

                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(AlbumsFragment.this).getCurrentDestination().getId() != R.id.searchFragment) {

                    findNavigationController().navigate(R.id.action_albumsFragment_to_searchFragment);
                }
            }
        });



    }
}

