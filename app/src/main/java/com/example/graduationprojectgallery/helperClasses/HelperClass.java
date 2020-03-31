package com.example.graduationprojectgallery.helperClasses;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.models.PhotoModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class HelperClass extends Fragment {

    //region tazzy albums data
    public static String[] albums_names_array;
    public static int albums_count = 0;

    //endregion
    public static List<PhotoModel> getPhotos(Context context) {
        // The list of columns we're interested in:
        String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.ImageColumns.TITLE, MediaStore.Images.Media.SIZE};

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
            final int size = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
            do {

                result.add(new PhotoModel(cursor.getString(imagePath), cursor.getString(date), cursor.getString(title), cursor.getString(size)));
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


        List<String> result = new ArrayList<>(cursor.getCount());

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

    public static String ConvertTimeStampToDate(Long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000L);
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

    //region tazzy albums methods
    public static void createAlbumDirectory(String album_name, Activity activity) {

        File sdcard = createAppDirectory(activity);

        //region creating albums directory
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission is NOT granted");
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    100); // this 100 here is MY_PERMISSIONS_REQUEST_READ_CONTACTS

        } else {
            Log.v(TAG, "Permission is granted");
        }


        String directory_name = "Albums";
        File albumsDir = new File(sdcard, directory_name);
        try {
            if (albumsDir.exists()) {
                Log.v(TAG, "album directory already exists!");
            }

            if (!albumsDir.exists()) {

                albumsDir.mkdir();
                Log.v(TAG, "album directory created!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //endregion

        //region creating albums txt file checking if album already exists
        File f1 = new File(sdcard, "Albums.txt");
        String[] words = null;

        try {
            FileReader fr = new FileReader(f1);  //Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
            String s;
            int count = 0;
            while ((s = br.readLine()) != null)   //Reading Content from the file
            {
                words = s.split("\\$");  //Split the word using space
                for (String word : words) {
                    if (word.equals(album_name))   //Search for the given word
                    {
                        count++;    //If Present increase the count by one
                        Log.d(TAG, "createAlbumDirectory: album already exists " + count);
                        Toast.makeText(activity, album_name + "Already Exists!", Toast.LENGTH_LONG).show();
                        fr.close();
                        br.close();
                        return;
                    }
                }
            }
            fr.close();
            br.close();
            Log.d(TAG, "createAlbumDirectory: " + album_name + " doesn't exist YET ");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //endregion

        //region adding the album to albums list

        FileOutputStream fout;
        try {
            fout = new FileOutputStream(new File(sdcard, "Albums.txt"), true);
            OutputStreamWriter writer = new OutputStreamWriter(fout);
            writer.write(album_name + "$");
            writer.flush();
            writer.close();
            Toast.makeText(activity, album_name + "Done!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        //region creating the album text file
        File f2 = new File(albumsDir, album_name + ".txt");
        try {
            f2.createNewFile();

            if (f2.exists()) {
                Log.d(TAG, album_name + "txt file created ");
            }
            if (!f2.exists()) {
                Log.d(TAG, album_name + "txt file NOT created ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //endregion

        loadAlbumsNames(activity);
    }

    public static void loadAlbumsNames(Activity activity) {

        File sdcard = createAppDirectory(activity);

        File f1 = new File(sdcard, "Albums.txt");
        String[] words = null;

        try {
            FileReader fr = new FileReader(f1);  //Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
            String s;
            int count = 0;
            while ((s = br.readLine()) != null)   //Reading Content from the file
            {
                words = s.split("\\$");  //Split the word using space


                albums_names_array = Arrays.copyOf(words, words.length);
                albums_count = words.length;

            }
            Log.d(TAG, "loadAlbumsNames: albums names are " + albums_names_array[0]);
            fr.close();
            br.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void goToAlbum(View view) { //tazzy this navigates to photosFragment
        NavController nav = Navigation.findNavController(view);
        nav.navigate(R.id.action_foryouFragment_to_photosFragment);
    }


    public static File createAppDirectory(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission is NOT granted");
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    100); // this 100 here is MY_PERMISSIONS_REQUEST_READ_CONTACTS

        } else {
            Log.v(TAG, "Permission is granted");
        }
        //region creating app directory
        String directory_path = Environment.getExternalStorageDirectory().toString();
        String directory_name = "bitirmeGaleri";
        File appDir = new File(directory_path, directory_name);
        try {
            if (appDir.exists()) {
                Log.v(TAG, "createAppDirectory : app directory already exists!");
                return appDir;
            }

            if (!appDir.exists()) {

                appDir.mkdir();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.v(TAG, "app directory created!");


        return appDir;
    }

    //endregion

}
