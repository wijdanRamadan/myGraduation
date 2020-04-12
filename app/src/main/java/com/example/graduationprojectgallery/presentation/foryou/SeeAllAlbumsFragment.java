package com.example.graduationprojectgallery.presentation.foryou;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.models.Album;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SeeAllAlbumsFragment extends BaseFragment implements EditAlbumsDialog.OnInputSelected {


    private ArrayList<Album> mAlbums = new ArrayList<>();

    static SeeAllAlbumsAdapter adapter;
    RecyclerView recyclerView;
    View view;
    View view2;
    public ImageView back_albums_button;
    public ImageView disabled_back_album_button;
    public TextView edit_albums_button;
    public TextView done_albums_button;
    public ArrayList<ImageView> minus_button;
    public ImageView minus_albums_button;
    static boolean first_click = false;

    @Override
    public void sendInput(String input, int position) {
        System.out.println(input);
        Toast.makeText(getContext(), "Dialog closed safely!!", Toast.LENGTH_LONG).show();
        HelperClass.deleteAlbum(input, getActivity());
        deleteAlbum(position);
    }


    //region crap I tried
    public interface OnMinusClicked {

        void showMinusIcon();

        void hideMinusIcon();

        void InitiateDeleteAlbumDialog(View v, int position);
    }

    public OnMinusClicked minusClicked = new OnMinusClicked() {
        @Override
        public void showMinusIcon() {
//            for(int position = 0; position <adapter.minus_button.size(); position++){
//            adapter.minus_button.get(position).animate().alpha(1.0f);
//            adapter.minus_button.get(position).setVisibility(View.VISIBLE);
//            adapter.notifyDataSetChanged();
            first_click = true;
            Toast.makeText(getContext(), "" + first_click, Toast.LENGTH_SHORT).show();
//        }

        }
        public void hideMinusIcon() {
//            for(int position = 0; position <minus_button.size(); position++) {
//                adapter.minus_button.get(position).animate().alpha(0.1f);
//                adapter.minus_button.get(position).setVisibility(View.GONE);
            SeeAllAlbumsFragment.first_click = false;
//                adapter.notifyDataSetChanged();
//            }
            //Toast.makeText(mContext, "" + first_click, Toast.LENGTH_SHORT).show();
        }


        public void InitiateDeleteAlbumDialog(View v, int position) {



        }


    };

//endregion

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide(); //tazzy this hides the original bar

        setHasOptionsMenu(false); // if menu added, this needs to be true


        BottomNavigationView navigationView = getActivity().findViewById(R.id.bottom_nav); //hides bottom navigation menu
        navigationView.setVisibility(View.GONE);

        LoadDataSet();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_display_all_albums, container, false);
        view2 = inflater.inflate(R.layout.display_albums_recycler_item, container, false);
        back_albums_button = view.findViewById(R.id.back_imageView2);
        back_albums_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavigationController().navigate(R.id.action_seeAllAlbumsFragment_to_albumsFragment);

            }
        });


        edit_albums_button = view.findViewById(R.id.edit_albums_button);
        disabled_back_album_button = view.findViewById(R.id.gray_back_imageView);
        done_albums_button = view.findViewById(R.id.done_albums_button);

        edit_albums_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_albums_button.setVisibility(View.GONE);
                disabled_back_album_button.setVisibility(View.VISIBLE);
                edit_albums_button.setVisibility(View.GONE);
                done_albums_button.setVisibility(View.VISIBLE);
                minusClicked.showMinusIcon();
                //  LoadDataSet();


            }
        });


        done_albums_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_albums_button.setVisibility(View.VISIBLE);
                disabled_back_album_button.setVisibility(View.GONE);
                edit_albums_button.setVisibility(View.VISIBLE);
                done_albums_button.setVisibility(View.GONE);
                minusClicked.hideMinusIcon();
                //  LoadDataSet();
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

    private void LoadDataSet() { //updating arraylist of the adapter
        Log.d(TAG, "SeeAllAlbum : loading data set");

        HelperClass.loadAlbums(this.getActivity());
        mAlbums.removeAll(mAlbums);
        for (String name : HelperClass.albums_names_array) {

            mAlbums = new ArrayList<Album>(HelperClass.mAlbums);
        }


    } //tazzy this is to have place holders for testing


    public void BuildRecyclerView() {

        recyclerView = view.findViewById(R.id.see_all_albums_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new SeeAllAlbumsAdapter(this.getContext(), mAlbums, SeeAllAlbumsFragment.this, getParentFragmentManager());
        recyclerView.setAdapter(adapter);

    }

    //TODO create custom menu for deletion
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.display_albums_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void deleteAlbum(int position) {

        mAlbums.remove(position);
        adapter.notifyItemRemoved(position);
    }

}
