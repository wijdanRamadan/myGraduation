package com.example.graduationprojectgallery.presentation.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.graduationprojectgallery.helperClasses.FirebaseFunctions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.graduationprojectgallery.activities.MainActivity.photosUris;

public class SearchViewModel extends AndroidViewModel {
    MutableLiveData<List<String>> imagePaths = new MutableLiveData<>();

    private List<String> images = new ArrayList<>();
    private Application context;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    //TODO pass photo uris as argument to fragment.
   void getImagesByLables(String lable) {
        FirebaseFunctions.getImagesBasedOnLabels(context, photosUris, lable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        images.add(s);
                    }
                }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                imagePaths.postValue(images);
            }
        })
                .subscribe();
    }
}
