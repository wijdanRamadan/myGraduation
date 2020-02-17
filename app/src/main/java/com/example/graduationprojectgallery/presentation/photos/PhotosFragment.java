package com.example.graduationprojectgallery.presentation.photos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.HelperClass;
import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.example.graduationprojectgallery.presentation.albums.AlbumsFragment;
import com.example.graduationprojectgallery.presentation.photos.adapter.PhotosFragmentAdapter;
import com.example.graduationprojectgallery.presentation.photos.slideshow.SlideShowDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.List;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class PhotosFragment extends BaseFragment implements PhotosFragmentAdapter.PhotoClickListener {

    static List<String> urls;


    private PhotosFragmentAdapter mAdapter;

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
        urls = HelperClass.getImagePaths(getContext());

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

        mAdapter = new PhotosFragmentAdapter(getActivity(), urls);
        mAdapter.setPhotoClickListener(this);

        recyclerView = view.findViewById(R.id.photos_recycler_view);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));


    }


    @Override
    // TODO: 2/17/2020  implement this method to open slide show fragment
    public void OnPhotoClick(int position ) {


            // findNavigationController().navigate(R.id.action_photosFragment_to_slideShowDialogFragment);


    }
}
