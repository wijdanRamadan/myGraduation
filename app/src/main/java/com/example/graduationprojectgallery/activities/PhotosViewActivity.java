package com.example.graduationprojectgallery.activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.helperClasses.HelperClass;
import com.example.graduationprojectgallery.models.PhotoModel;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;

import java.io.InputStream;
import java.util.List;

import static com.example.graduationprojectgallery.helperClasses.HelperClass.pathToUri;



public class PhotosViewActivity extends AppCompatActivity {

    PhotoView photoView;
    String photoPath;
    PhotoModel photoModel;
    ArrayList<PhotoModel> tempList = new ArrayList<>();

    public static PhotoModel photoModel1;

   Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photos_view);
        Intent intent = getIntent();

        photoView = findViewById(R.id.photoView);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        toolbar=findViewById(R.id.photos_view_toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setActionBar(toolbar);
        }

        photoModel= (PhotoModel) intent.getSerializableExtra("photo");

        if (photoModel!=null)
        photoPath= photoModel.getPath();

        Bitmap bitmap = null;
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(pathToUri(photoModel.getPath()));
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

try {


    HelperClass.show(bitmap, this, photoView);
}
catch (Exception e)
{}



        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.share) {
                    ApplicationInfo applicationInfo = getApplicationContext().getApplicationInfo();
                    String apkPath = applicationInfo.sourceDir;
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("Application/vnd.android.package-archive");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
                    startActivity(Intent.createChooser(intent, "ShareVia"));

                }

                   // HelperClass.DeletePhoto(photoModel,getBaseContext());

                if (id == R.id.favorite){
                    tempList.add(photoModel);
                    HelperClass.addImageToAlbum(tempList, "Favorites", PhotosViewActivity.this);
                    tempList.clear();
                }

                return true;

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_detailes_menu , menu);

        menu.add("title : "+photoModel.getTitle());
        menu.add("date : "+HelperClass.ConvertTimeStampToDate(Long.valueOf(photoModel.getDate())));
     //   menu.add("size : " +HelperClass.getImageSize(Long.valueOf(photoModel.getSize())));
        return true;
    }

}
