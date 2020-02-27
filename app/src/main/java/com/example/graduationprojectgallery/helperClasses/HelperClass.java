package com.example.graduationprojectgallery.helperClasses;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.graduationprojectgallery.models.PhotoModel;

import java.util.ArrayList;
import java.util.List;


public class HelperClass {


    public static List<PhotoModel> getPhotos(Context context) {
        // The list of columns we're interested in:
        String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.ImageColumns.TITLE };

        final Cursor cursor = context.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // Specify the provider
                        columns, // The columns we're interested in
                        null, // A WHERE-filter query
                        null, // The arguments for the filter-query
                        MediaStore.Images.Media.DATE_ADDED + " DESC" // Order the results, newest first
                );


        List<PhotoModel> result = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {

            final int imagePath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            final int date = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED);
            final int title = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
            do {

                result.add(new PhotoModel(cursor.getString(imagePath) , cursor.getString(date) ,cursor.getString(title)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return result;
    }

    public static List<String> getImagePaths(Context context) {
        // The list of columns we're interested in:
        String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};

        final Cursor cursor = context.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // Specify the provider
                        columns, // The columns we're interested in
                        null, // A WHERE-filter query
                        null, // The arguments for the filter-query
                        MediaStore.Images.Media.DATE_ADDED + " DESC" // Order the results, newest first
                );

        List<String> result = new ArrayList<String>(cursor.getCount());

        if (cursor.moveToFirst()) {
            final int image_path_col = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            do {
                result.add(cursor.getString(image_path_col));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return result;
    }


    public static void DeletePhoto(String path, Context context) {
        String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};
        final int cursor = context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, path, columns);


    }


    public static void show(final String photo, Context context, ImageView container) {
        Glide
                .with(context)
                .load(photo)
                .override(300, 300)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(container);
    }

    public static void show(final PhotoModel photo, Context context, ImageView container) {
        Glide
                .with(context)
                .load(photo.getPath())
                .override(300, 300)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(container);

    }
}
