package com.example.graduationprojectgallery.presentation.photos.slideshow;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.graduationprojectgallery.HelperClass;
import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.base.BaseFragment;
import com.example.graduationprojectgallery.presentation.photos.slideshow.adapter.ImageSliderPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class SlideShowDialogFragment extends BaseFragment {

    private ImageSliderPagerAdapter imageSliderPagerAdapter ;
    private ViewPager viewPager;

    BottomNavigationView bottomNavigationView ;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SlideShowDialogFragment() {
    }

    public static SlideShowDialogFragment newInstance(String param1, String param2) {
        SlideShowDialogFragment fragment = new SlideShowDialogFragment();
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
        bottomNavigationView= getActivity().findViewById(R.id.bottom_nav);
        bottomNavigationView.setVisibility(View.INVISIBLE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slide_show_dialog, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager=(ViewPager)view.findViewById(R.id.slideShowDialogFragment_viewPager);
        imageSliderPagerAdapter = new ImageSliderPagerAdapter(getContext());
        viewPager.setAdapter(imageSliderPagerAdapter);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
