package com.example.graduationprojectgallery.helperClasses;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.media.MediaRouter;

import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.graduationprojectgallery.BuildConfig;
import com.example.graduationprojectgallery.activities.MainActivity;
import com.example.graduationprojectgallery.models.Album;

import com.example.graduationprojectgallery.activities.MainActivity;
import com.example.graduationprojectgallery.models.PhotoModel;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

import static android.net.Uri.fromFile;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.graduationprojectgallery.activities.MainActivity.photos;


import static com.example.graduationprojectgallery.activities.MainActivity.photos;


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
                .load(pathToUri(photo.getPath()))
                .override(300, 300)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(container);

    }

    public static void show(Bitmap bitmap, Context context, ImageView container) {
        Glide
                .with(context)
                .load(bitmap)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(container);

    }

    public static String ConvertTimeStampToDate(Long time) {
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

    public static Uri pathToUri(String path)
    {
        return Uri.fromFile(new File(path));
    }

    public static Bitmap pathToBitmap(Context context , String path)
    {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(pathToUri(path));
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public static List<Uri> getPhotosUris(MainActivity mainActivity) {
        List<Uri> result = new ArrayList<>();
        for (PhotoModel photo: photos) {
            result.add(pathToUri(photo.getPath()));
        }

        return result;
    }

    //region tazzy albums methods

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

            }

            if (!appDir.exists()) {

                appDir.mkdir();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.v(TAG, "app directory created!");
        //endregion

        //region creating albums directory
        directory_name = "Albums";
        File albumsDir = new File(appDir, directory_name);
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

        //region creating the albums text file
        File f1 = new File(appDir, "Albums.txt");
        try {
            f1.createNewFile();

            if (f1.exists()) {
                Log.d(TAG, "Albums.txt file created ");
            }
            if (!f1.exists()) {
                Log.d(TAG, "Albums.txt file NOT created ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //endregion

        return appDir;
    }

    public static void createNewAlbumDirectory(String album_name, Activity activity) {
        album_already_exists = false;
        File appDir = createAppDirectory(activity);

        //region creating/opening albums directory
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission is NOT granted");
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    100); // this 100 here is MY_PERMISSIONS_REQUEST_READ_CONTACTS

        } else {
            Log.v(TAG, "Permission is granted");
        }


        String directory_name = "Albums";
        File albumsDir = new File(appDir, directory_name);
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

        //region opening albums txt file checking if album already exists
        File f1 = new File(appDir, "Albums.txt");
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
                        Log.d(TAG, "createNewAlbumDirectory: album already exists " + count);
                        //Toast.makeText(activity, album_name + "Already Exists!", Toast.LENGTH_LONG).show();
                        album_already_exists = true;
                        fr.close();
                        br.close();

                        return;
                    }
                }
            }
            fr.close();
            br.close();
            Log.d(TAG, "createNewAlbumDirectory: " + album_name + " doesn't exist YET ");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //endregion

        //region adding the album to albums list

        FileOutputStream fout;
        try {
            fout = new FileOutputStream(new File(appDir, "Albums.txt"), true);
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

        //region create favorites album txt file

        File f3 = new File(albumsDir, "Favorites.txt");
        try {
            f3.createNewFile();

            if (f3.exists()) {
                Log.d(TAG, "Favorites.txt file created ");
            }
            if (!f3.exists()) {
                Log.d(TAG, "Favorites.txt file NOT created ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //endregion

        //Tazzy PLEASE NOTE THAT ALBUM EXISTENCE NEEDS TO BE CHECKED BEFORE CALLING THIS
        loadAlbums(activity);
    }

    public static void loadAlbums(Activity activity) {
        Log.d(TAG, "loadAlbums: albums names are ");
        File appDirectory = createAppDirectory(activity);

        File f1 = new File(appDirectory, "Albums.txt");
        String[] words = null;
        mAlbums.clear();
        try {
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);

            Uri uri;
            String s;
            int count = 0;

            albums_names_array = new String[2];

            albums_count = 2;
            while ((s = br.readLine()) != null)   //Reading Content from the file
            {
                words = s.split("\\$");  //Split the word using space

                albums_names_array = new String[2 + words.length];
                for (String word : words) {

                    albums_names_array[count + 2] = words[count];
                    count++;
                    //  albums_names_array = Arrays.copyOf(words, words.length);
                }
                albums_count = words.length;
            }
            albums_names_array[0] = "Recent";
            albums_names_array[1] = "Favorites";


            fr.close();
            br.close();

            for (int i = 0; i < albums_names_array.length; i++) {

                mAlbums.add(new Album(albums_names_array[i], getAlbumThumbnail(albums_names_array[i], activity)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Uri> getAlbumImages(String album_name, Activity activity) {

        ArrayList<Uri> album_images_uri = new ArrayList<>();
        File appDirectory = createAppDirectory(activity);

        //region checking/creating the album text file
        File albumsDir = new File(appDirectory, "Albums");
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

        //region reading the album name txt file for images

        String s;
        try {
            FileReader fr = new FileReader(f2);
            BufferedReader br = new BufferedReader(fr);
            String[] words = null;

            while ((s = br.readLine()) != null)   //Reading Content from the file
            {
                words = s.split("\\$");  //Split the word using space


                for (String word : words) {
                    album_images_uri.add(Uri.parse(word));
                }
            }

            fr.close();
            br.close();


        } catch (Exception e) {
            e.printStackTrace();

        }

        //endregion

        return album_images_uri;

    }

    public static Uri getAlbumThumbnail(String album_name, Activity activity) {

        File appDirectory = createAppDirectory(activity);
        File albumsDir = new File(appDirectory, "Albums");
        Uri uri = Uri.fromFile(new File(albumsDir, "empty_album.png"));
        empty_icon = Uri.fromFile(new File(albumsDir, "empty_album.png"));
        if (album_name == "Recent") {

            uri = fromFile(new File(photos.get(1).getPath()));
            return uri;
        }

        //region checking/creating the album text file
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

        String s;
        try {
            FileReader fr = new FileReader(f2);
            BufferedReader br = new BufferedReader(fr);
            String[] words = null;

            while ((s = br.readLine()) != null)   //Reading Content from the file
            {
                words = s.split("\\$");  //Split the word using $


                for (String word : words) {

                    uri = Uri.parse(words[words.length - 1]);
                    fr.close();
                    br.close();

                    return uri;
                }

            }

            fr.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


        return uri;
    }


    public static void addUrisToAlbum(ArrayList<Uri> album_uris, String album_name, Activity activity) {

        File appDirectory = createAppDirectory(activity);

        //region checking/creating the album text file
        File albumsDir = new File(appDirectory, "Albums");
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

        //region writing image to file

        FileOutputStream fout;
        try {
            fout = new FileOutputStream(f2, true);
            OutputStreamWriter writer = new OutputStreamWriter(fout);

            for (int i = 0; i < album_uris.size(); i++) {
                writer.write(album_uris.get(i) + "$");
            }

            writer.flush();
            writer.close();
            Toast.makeText(activity, album_name + "Done!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        album_uris.removeAll(album_uris);

        //endregion

    }

    public static Bitmap pathToBitmap(Context context , String path)
    {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(pathToUri(path));
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //endregion

        //region eliminating duplicates
        ArrayList<Uri> existing_images = new ArrayList<>();
        existing_images = getAlbumImages(album_name, activity);
        ArrayList<Uri> new_images = new ArrayList<>();

        for (PhotoModel photo : photoModels) {
            new_images.add(pathToUri(photo.getPath()));
        }
        ArrayList<Uri> union = new ArrayList<Uri>(new_images);
        union.addAll(existing_images);

        ArrayList<Uri> intersection = new ArrayList<Uri>(new_images);
        intersection.retainAll(existing_images);

        union.removeAll(intersection);

        //endregion

        //region writing image to file


        FileOutputStream fout;
        try {
            fout = new FileOutputStream(f2, true);
            OutputStreamWriter writer = new OutputStreamWriter(fout);

            for (int i = 0; i < photoModels.size(); i++) {
                writer.write(union.get(i) + "$");
            }

            writer.flush();
            writer.close();
            Toast.makeText(activity, album_name + "Done!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        photoModels.removeAll(photoModels);

        //endregion

    }

    public static void deleteAlbum(String album_name, Activity activity) {

        File appDir = createAppDirectory(activity);

        return bitmap;
    }



    public static List<Uri> getPhotosUris(MainActivity mainActivity) {
        List<Uri> result = new ArrayList<>();
        for (PhotoModel photo: photos) {
            result.add(pathToUri(photo.getPath()));
        }

        return result;
    }
}
