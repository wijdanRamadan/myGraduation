package com.example.graduationprojectgallery.presentation.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;


import com.example.graduationprojectgallery.helperClasses.FirebaseFunctions;
import com.example.graduationprojectgallery.models.PhotoModel;
import com.example.graduationprojectgallery.presentation.search.adapter.SearchFragmentPhotosAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;
import java.util.List;

import static androidx.navigation.fragment.NavHostFragment.findNavController;
import static com.example.graduationprojectgallery.activities.MainActivity.photos;


public class SearchFragment extends BaseFragment {

    Toolbar toolbar;
    SearchView simpleSearchView;
    List<PhotoModel> photoModelList  = new ArrayList<>();




    private SearchFragmentPhotosAdapter mAdapter;
    private SearchFragmentPhotosAdapter.PhotoClickListener photoClickListener;
    private RecyclerView recyclerView;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        for (int i=0 ; i<3 ; i++)
        {
            photoModelList.add(photos.get(i));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        toolbar = getActivity().findViewById(R.id.app_toolbar);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    public void setNav() {
        BottomNavigationItemView photos = getActivity().findViewById(R.id.photos);
        BottomNavigationItemView albums = getActivity().findViewById(R.id.albums);
        BottomNavigationItemView search = getActivity().findViewById(R.id.search);


        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(SearchFragment.this).getCurrentDestination().getId() != R.id.photosFragment) {
                    findNavigationController().navigate(R.id.action_searchFragment_to_photosFragment);
                }
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(SearchFragment.this).getCurrentDestination().getId() != R.id.albumsFragment) {

                    findNavigationController().navigate(R.id.action_searchFragment_to_albumsFragment);
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findNavController(SearchFragment.this).getCurrentDestination().getId() !=R.id.searchFragment)
                {

                }
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        simpleSearchView = (SearchView)getActivity().findViewById(R.id.searchbar);

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

              simpleSearchView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mAdapter = new SearchFragmentPhotosAdapter(getActivity() , photoModelList);
                        recyclerView = getActivity().findViewById(R.id.search_fragment_photos_recycler_view);
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));


                    }
                });




                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search_option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }





}


