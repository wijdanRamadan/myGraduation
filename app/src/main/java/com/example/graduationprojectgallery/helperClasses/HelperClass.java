package com.example.graduationprojectgallery.helperClasses;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.graduationprojectgallery.models.PhotoModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class HelperClass {


    public static List<PhotoModel> getPhotos(Context context) {
        // The list of columns we're interested in:
        String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.ImageColumns.TITLE , MediaStore.Images.Media.SIZE};

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
            final int size=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
            do {

                result.add(new PhotoModel(cursor.getString(imagePath), cursor.getString(date), cursor.getString(title),cursor.getString(size)));
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

  // TODO: 3/3/2020 implement delete photo
    public static void DeletePhoto(PhotoModel path, Context context) {
      /*  String[] columns = {MediaStore.Images.Media.DATA};
        final int cursor = context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, path.getTitle(), columns);
        if (cursor == -1) {

        }
        Toast.makeText(context, "-1", Toast.LENGTH_LONG).show();*/

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

    public static String ConvertTimeStampToDate(Long time )
    {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time* 1000L);
        String date = DateFormat.format("dd-MM-yyyy hh:mm", cal).toString();
        return date;
    }

    public static String getImageSize(long bytes) {
        long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        return b < 1024L ? bytes + " B"
                : b <= 0xfffccccccccccccL >> 40 ? String.format("%.1f KB", bytes / 0x1p10)
                : b <= 0xfffccccccccccccL >> 30 ? String.format("%.1f MB", bytes / 0x1p20)
                : b <= 0xfffccccccccccccL >> 20 ? String.format("%.1f GB", bytes / 0x1p30)
                : b <= 0xfffccccccccccccL >> 10 ? String.format("%.1f TB", bytes / 0x1p40)
                : b <= 0xfffccccccccccccL ? String.format("%.1f PB", (bytes >> 10) / 0x1p40)
                : String.format("%.1f EB", (bytes >> 20) / 0x1p40);
    }

}
