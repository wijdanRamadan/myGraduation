package com.example.graduationprojectgallery.helperClasses;


import android.content.Context;
import android.database.Observable;
import android.net.Uri;


import androidx.annotation.NonNull;

import com.example.graduationprojectgallery.models.PhotoModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetector;
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.graduationprojectgallery.activities.MainActivity.photos;

public class FirebaseFunctions  {
    static String x = null;


    public static List<PhotoModel> getImagesBasedOnLabels(Context context,List<PhotoModel> photoModels, final String searchedItem) {

        final List<PhotoModel> result = new ArrayList<>();


        FirebaseVisionObjectDetectorOptions options =
                new FirebaseVisionObjectDetectorOptions.Builder()
                        .setDetectorMode(FirebaseVisionObjectDetectorOptions.SINGLE_IMAGE_MODE)
                        .enableMultipleObjects()
                        .enableClassification()
                        .build();

        FirebaseVisionObjectDetector objectDetector =
                FirebaseVision.getInstance().getOnDeviceObjectDetector(options);
        FirebaseVisionImage image;
        FirebaseVisionImageLabeler labeler = FirebaseVision.getInstance()
                .getOnDeviceImageLabeler();
        try {

            for (final PhotoModel photo : photoModels) {
                image = FirebaseVisionImage.fromFilePath(context, Uri.fromFile(new File(photo.getPath())));

                labeler.processImage(image)
                        .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
                            @Override
                            public void onSuccess(List<FirebaseVisionImageLabel> labels) {
                                for (FirebaseVisionImageLabel label : labels) {
                                    if (searchedItem.equals( label.getText())) {
                                        result.add(photo);
                                    }
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // ...
                            }
                        });


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    public  static String testFunction(PhotoModel photoModel, Context context)
    {

        FirebaseVisionObjectDetectorOptions options =
                new FirebaseVisionObjectDetectorOptions.Builder()
                        .setDetectorMode(FirebaseVisionObjectDetectorOptions.SINGLE_IMAGE_MODE)
                        .enableMultipleObjects()
                        .enableClassification()
                        .build();

        FirebaseVisionObjectDetector objectDetector =
                FirebaseVision.getInstance().getOnDeviceObjectDetector(options);
        FirebaseVisionImage image;
        FirebaseVisionImageLabeler labeler = FirebaseVision.getInstance()
                .getOnDeviceImageLabeler();


        try {
            image = FirebaseVisionImage.fromFilePath(context, Uri.fromFile(new File(photoModel.getPath())));
            labeler.processImage(image)
                    .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
                        @Override
                        public void onSuccess(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
                            x =firebaseVisionImageLabels.get(0).getText();
                        }
                    });
        } catch (IOException e) {


            e.printStackTrace();
        }






        return x;
    }


    public static void test()
    {
       Observable<PhotoModel> observable;

    }



}

