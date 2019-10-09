package com.example.graduationprojectgallery.base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import com.example.graduationprojectgallery.R;
import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class BaseFragment extends Fragment {

    protected NavController findNavigationController() {
        return findNavController(this);
    }

  /*  @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView photos = getActivity().findViewById(R.id.photos);
        TextView foryou = getActivity().findViewById(R.id.foryou);
        TextView albums = getActivity().findViewById(R.id.albums);
        TextView search = getActivity().findViewById(R.id.search);

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(BaseFragment.this).getCurrentDestination().getId()!= R.id.photosFragment){
                    findNavigationController().navigate(R.id.action_mainActivityFragment_to_photosFragment);
                }
            }
        });
        // TODO: fix navigation bug :D

      foryou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(BaseFragment.this).getCurrentDestination().getId()!= R.id.foryouFragment){

                    findNavigationController().navigate(R.id.action_mainActivityFragment_to_foryouFragment);
                }
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(BaseFragment.this).getCurrentDestination().getId()!= R.id.albumsFragment) {

                    findNavigationController().navigate(R.id.action_mainActivityFragment_to_albumsFragment);
                }            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findNavController(BaseFragment.this).getCurrentDestination().getId()!= R.id.searchFragment) {

                    findNavigationController().navigate(R.id.action_mainActivityFragment_to_searchFragment);
                }            }
        });
*/


}
