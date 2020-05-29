package com.example.graduationprojectgallery.helperClasses;

import android.os.SystemClock;

import com.example.graduationprojectgallery.models.PhotoModel;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.graduationprojectgallery.activities.MainActivity.photos;

public class FaceRecognition {

    public static final String BASE_URL = "http://192.168.1.50:5000/all";
    public static final String KNOWN_URL = "http://192.168.1.50:5000/known";

   public static void SendEverything(){

        for (PhotoModel photo : photos) {
            final MediaType MEDIA_TYPE_PNG = MediaType.parse(photo.getPath());
            System.out.println(photo.getPath());
            File file = new File(photo.getPath());
            OkHttpClient client = new OkHttpClient();
            RequestBody requestFile = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", photo.getPath(), RequestBody.create(MEDIA_TYPE_PNG, file))
                    .build();

            Request request = new Request.Builder()
                    .url(BASE_URL)
                    .post(requestFile)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // Cancel the post on failure.
                    e.printStackTrace();
//                    call.cancel();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    System.out.println(response);
                }
            });
            SystemClock.sleep(500);
        }

    }

public static void send_known(PhotoModel photo, String name){


        final MediaType MEDIA_TYPE_PNG = MediaType.parse(photo.getPath());
        System.out.println(photo.getPath());
        File file = new File(photo.getPath());
        OkHttpClient client = new OkHttpClient();
        RequestBody requestFile = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", photo.getPath(), RequestBody.create(MEDIA_TYPE_PNG, file))
                .addFormDataPart("name", name)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(requestFile)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                e.printStackTrace();
//                    call.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                System.out.println(response);
            }
        });

    }



}
