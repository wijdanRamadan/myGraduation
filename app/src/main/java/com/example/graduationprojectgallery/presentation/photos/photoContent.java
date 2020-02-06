package com.example.graduationprojectgallery.presentation.photos;

import android.net.Uri;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class photoContent {
    static final List<photoModel> ITEMS = new ArrayList<>();

    public static void loadImage(File file) {
        photoModel newItem = new photoModel();
        newItem.uri = Uri.fromFile(file);
        newItem.date = getDateFromUri(newItem.uri);
        addItem(newItem);
    }

    private static void addItem(photoModel item) {
        ITEMS.add(0, item);
}
    public static void loadSavedImages(File dir) {
        ITEMS.clear();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                String absolutePath = file.getAbsolutePath();
                String extension = absolutePath.substring(absolutePath.lastIndexOf("."));
                if (extension.equals(".jpg")) {
                    loadImage(file);
                }
            }
        }
    }

    private static String getDateFromUri(Uri uri){
        String[] split = uri.getPath().split("/");
        String fileName = split[split.length - 1];
        String fileNameNoExt = fileName.split("\\.")[0];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(new Date(Long.parseLong(fileNameNoExt)));
        return dateString;
    }


}
