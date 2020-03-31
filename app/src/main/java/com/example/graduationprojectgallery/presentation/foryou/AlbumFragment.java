package com.example.graduationprojectgallery.presentation.foryou;


import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.presentation.photos.PhotosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class AlbumFragment extends BaseFragment implements NewAlbumDialog.OnInputSelected {

    @Override
    public void sendInput(String input) {  //tazzy input is the name of new album entered by user
        System.out.println(input);
        HelperClass.createAlbumDirectory(input, getActivity());
    }

    //region crap tazzy didn't create
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "AlbumAdapter";


    private String mParam1;
    private String mParam2;
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    //endregion
    private ArrayList<String> mImageUrls = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlbumFragment.
     */

    RecyclerView recyclerView;
    View view;
    LayoutInflater inflater;
    ViewGroup container;
    ActionBar actionBar;
    public ImageView new_album_plus_button;
    public TextView new_album_name_input;
    Toolbar toolbar;

    // TODO: Rename and change types and number of parameters
    public static AlbumFragment newInstance(String param1, String param2) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);


        return fragment;


    }

    public void setNav() {

        BottomNavigationItemView photos = getActivity().findViewById(R.id.photos);
        BottomNavigationItemView albums = getActivity().findViewById(R.id.albums);
        BottomNavigationItemView search = getActivity().findViewById(R.id.search);

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(AlbumFragment.this).getCurrentDestination().getId() != R.id.photosFragment) {
                    findNavigationController().navigate(R.id.action_foryouFragment_to_photosFragment);
                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(AlbumFragment.this).getCurrentDestination().getId() != R.id.searchFragment) {

                    findNavigationController().navigate(R.id.action_foryouFragment_to_searchFragment);
                }
            }
        });


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide(); //tazzy this hides the original bar
        //getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        toolbar=getActivity().findViewById(R.id.app_toolbar);


        getImages();
    }


    private void getImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        HelperClass.loadAlbumsNames(this.getActivity());

        for (String name : HelperClass.albums_names_array) {

            mNames.add(name);
            mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");

        }


    } //tazzy this is to have place holders for testing


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_album, container, false);
        new_album_plus_button = view.findViewById(R.id.plus_imageView2);


        new_album_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //tazzy create new album dialog
                System.out.println("new album clicked");
                NewAlbumDialog dialog = new NewAlbumDialog();

                dialog.setTargetFragment(AlbumFragment.this, 1);

                dialog.show(getFragmentManager(), "NewAlbumDialog");

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setNav();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.foryou_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);
        AlbumAdapter adapter = new AlbumAdapter(this.getContext(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);

        toolbar.setTitle(R.string.albums);
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.plus);
        toolbar.setOverflowIcon(drawable);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.albums_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}