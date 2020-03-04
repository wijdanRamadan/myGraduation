package com.example.graduationprojectgallery.presentation.foryou;


import android.app.ActionBar;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Transaction;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.example.graduationprojectgallery.presentation.foryou.adapter.RecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class ForyouFragment extends BaseFragment implements NewAlbumDialog.OnInputSelected {

    @Override
    public void sendInput(String input) { //tazzy that's
        System.out.println(input);
    }

    //region crap tazzy didn't create
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    View view;
    LayoutInflater inflater;
    ViewGroup container;
    ActionBar actionBar;
    public ImageView new_album_plus_button;
    public TextView new_album_name_input;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForyouFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForyouFragment newInstance(String param1, String param2) {
        ForyouFragment fragment = new ForyouFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);



        return fragment;


    }


    public void setNav()
    {

        BottomNavigationItemView photos = getActivity().findViewById(R.id.photos);
        BottomNavigationItemView foryou = getActivity().findViewById(R.id.foryou);
        BottomNavigationItemView albums = getActivity().findViewById(R.id.albums);
        BottomNavigationItemView search = getActivity().findViewById(R.id.search);
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(ForyouFragment.this).getCurrentDestination().getId() != R.id.photosFragment) {
                    findNavigationController().navigate(R.id.action_foryouFragment_to_photosFragment);
                }
            }
        });
        // TODO: fix navigation bug :D

        foryou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(ForyouFragment.this).getCurrentDestination().getId() != R.id.foryouFragment) {

                }
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(ForyouFragment.this).getCurrentDestination().getId() != R.id.albumsFragment) {
                    findNavigationController().navigate(R.id.action_foryouFragment_to_albumsFragment);

                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(ForyouFragment.this).getCurrentDestination().getId() != R.id.searchFragment) {

                    findNavigationController().navigate(R.id.action_foryouFragment_to_searchFragment);
                }
            }
        });



    }
//endregion

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public DialogFragment dialog;

    // public Dialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();


        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        getImages();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // new_album_plus_button = (ImageView) getActivity().findViewById(R.id.plus_imageView2);
        recyclerView = view.findViewById(R.id.foryou_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.getContext(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);


    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Washington");


    } //tazzy this is to have place holders for testing


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_foryou, container, false);
        new_album_plus_button = view.findViewById(R.id.plus_imageView2);
        new_album_plus_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                System.out.println("new album clicked");
                NewAlbumDialog dialog = new NewAlbumDialog();

                dialog.setTargetFragment(ForyouFragment.this, 1);

                dialog.show(getFragmentManager(), "NewAlbumDialog");
                return false;
            }
        }); //tazzy tried onclick instead and it wasn't as efficient
//        new_album_plus_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("new album clicked");
//                NewAlbumDialog dialog = new NewAlbumDialog();
//
//                dialog.setTargetFragment(ForyouFragment.this, 1);
//
//                dialog.show(getFragmentManager(), "NewAlbumDialog");
//            }
//        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setNav();
    }


}
