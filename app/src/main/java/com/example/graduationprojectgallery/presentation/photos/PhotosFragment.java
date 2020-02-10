package com.example.graduationprojectgallery.presentation.photos;


import android.net.Uri;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.example.graduationprojectgallery.R;

import com.example.graduationprojectgallery.base.BaseFragment;


import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class PhotosFragment extends BaseFragment  {

    File ximgFile;
    Uri xuri;

    private static final int SPAN_COUNT = 3;

    private List<photoModel> test;
    photoModel x = new photoModel();
    photoModel y = new photoModel();
    photoModel z  = new photoModel();

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
        test = new ArrayList<>();
        ximgFile = new  File("/storage/emulated/0/DCIM/Camera/20200206_222309.jpg");



        test.add(x);
        test.add(y);
        test.add(z);


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
        BottomNavigationItemView foryou = getActivity().findViewById(R.id.foryou);
        BottomNavigationItemView albums = getActivity().findViewById(R.id.albums);
        BottomNavigationItemView search = getActivity().findViewById(R.id.search);

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId() != R.id.photosFragment) {
                }
            }
        });


        foryou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId() != R.id.foryouFragment) {

                    findNavigationController().navigate(R.id.action_photosFragment_to_foryouFragment);
                }
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(PhotosFragment.this).getCurrentDestination().getId() != R.id.albumsFragment) {

                    findNavigationController().navigate(R.id.action_photosFragment_to_albumsFragment2);
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

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.hello) ;

       Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);



    }

    

}
