package com.example.graduationprojectgallery.presentation.search;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.graduationprojectgallery.R;
import com.example.graduationprojectgallery.activities.MainActivity;
import com.example.graduationprojectgallery.helperClasses.FirebaseFunctions;
import com.example.graduationprojectgallery.models.PhotoModel;

import static com.example.graduationprojectgallery.MyApplication.CHANNEL_ID;
import static com.example.graduationprojectgallery.activities.MainActivity.photos;


public class detectObjectService extends Service {

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);




        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
