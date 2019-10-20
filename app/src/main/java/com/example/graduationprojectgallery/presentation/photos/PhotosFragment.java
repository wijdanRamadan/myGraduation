package com.example.graduationprojectgallery.presentation.photos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class PhotosFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhotosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotosFragment.
     */
    // TODO: Rename and change types and number of parameters
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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        
    }
    public void setNav ()
    {
        BottomNavigationItemView photos = getActivity().findViewById(R.id.photos);
        BottomNavigationItemView foryou = getActivity().findViewById(R.id.foryou);
        BottomNavigationItemView albums = getActivity().findViewById(R.id.albums);
        BottomNavigationItemView search = getActivity().findViewById(R.id.search);


        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId()!= R.id.photosFragment){
                }
            }
        });
        // TODO: fix navigation bug :D

        foryou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId()!= R.id.foryouFragment){

                    findNavigationController().navigate(R.id.action_photosFragment_to_foryouFragment);
                }
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId()!= R.id.albumsFragment) {

                    findNavigationController().navigate(R.id.action_photosFragment_to_albumsFragment2);
                }            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId()!= R.id.searchFragment) {

                    findNavigationController().navigate(R.id.action_photosFragment_to_searchFragment2);
                }            }
        });

    }
}
