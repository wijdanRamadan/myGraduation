package com.example.graduationprojectgallery.presentation.foryou;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.example.graduationprojectgallery.helperClasses.Create_Empty_Icon;
import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.models.Album;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;

import static android.net.Uri.fromFile;
import static androidx.navigation.fragment.NavHostFragment.findNavController;
import static com.example.graduationprojectgallery.activities.MainActivity.urls;

public class AlbumFragment extends BaseFragment implements NewAlbumDialog.OnInputSelected {

    private ArrayList<Album> mAlbums = new ArrayList<>();
    private ArrayList<Uri> mUri = new ArrayList<>();
    static AlbumAdapter adapter;
    RecyclerView recyclerView;
    View view;
    GridLayoutManager layoutManager;
    Toolbar toolbar;
    public ImageView new_album_plus_button;
    public TextView see_all_button;
    String new_album;


    //tazzy this :https://www.youtube.com/watch?v=LGpf6PBZ3uw&list=PLcOTVcLpJoBXaQQkoiloQDrmyuTEv6E-2&index=2

    @Override //tazzy input is the name of new album ENTERED by user
    public void sendInput(String input) {
        System.out.println(input);
        new_album = input;
        insertAlbum(input);
    }

    //region crap tazzy didn't create
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "AlbumAdapter";


    //endregion


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

        setHasOptionsMenu(true);

        LoadDataSet();

    }

    //TODO fix this mess with photos selection
    public void insertAlbum(String input) {
        HelperClass.createNewAlbumDirectory(input, getActivity());
        if (!HelperClass.album_already_exists) {
            mAlbums.add(new Album(new_album, HelperClass.empty_icon));
            Uri uri = fromFile(new File(urls.get(1)));
            mUri.add(uri);
            uri = fromFile(new File(urls.get(2)));
            mUri.add(uri);
            uri = fromFile(new File(urls.get(3)));
            mUri.add(uri);
            HelperClass.addImageToAlbum(mUri, new_album, getActivity());
            adapter.notifyItemInserted(mAlbums.size() - 1);
            recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
        }
        if (HelperClass.album_already_exists) {
            Toast.makeText(getContext(), new_album + " alreay exists!", Toast.LENGTH_SHORT).show();
            recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
        }
    }

    private void LoadDataSet() {
        Log.d(TAG, "Album Fragment : LoadDataSet");

        HelperClass.loadAlbums(this.getActivity());
        mAlbums.removeAll(mAlbums);
        for (String name : HelperClass.albums_names_array) {

            mAlbums = new ArrayList<Album>(HelperClass.mAlbums);
        }


    }


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
                dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                dialog.show(getFragmentManager(), "NewAlbumDialog");

            }
        });

        see_all_button = view.findViewById(R.id.see_all_button);

        see_all_button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                findNavigationController().navigate(R.id.action_albumsFragment_to_seeAllAlbumsFragment);

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
        BuildRecyclerView();


    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.albums_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();

        BottomNavigationView navigationView = getActivity().findViewById(R.id.bottom_nav);
        navigationView.setVisibility(View.VISIBLE);

    }

    public void BuildRecyclerView() {

        Create_Empty_Icon create_empty_icon = new Create_Empty_Icon(getActivity()); //this loads the empty album icon to phone
        recyclerView = view.findViewById(R.id.foryou_recycleView);
        layoutManager = new GridLayoutManager(this.getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new AlbumAdapter(this.getContext(), mAlbums);
        recyclerView.setAdapter(adapter);

    }

}