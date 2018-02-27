package com.example.hp6300pro.demngayyeu.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import android.widget.RemoteViews;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.activity.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by HP 6300 Pro on 12/20/2017.
 */

public class NotifyService extends Service {
    private RemoteViews mContentView;
    private NotificationCompat.Builder mNotify;
    private NotificationManager mNotifyManager;
    private static final int NOTIFY_ID = 2;
    private long hieuThoiGian;
    private long mTime = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent it, int flags, int startId) {
        createNotification();
        mTime = it.getLongExtra(MainActivity.TIME_START, 0);
        if(mTime != 0){

            if (it.getStringExtra(MainActivity.IMG_BOY) != null) {
                Uri fileUri = Uri.parse(it.getStringExtra(MainActivity.IMG_BOY));

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);

                    mContentView.setImageViewBitmap(R.id.img_boy_notify, bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            if (it.getStringExtra(MainActivity.IMG_GIRL) != null) {
                Uri fileUri = Uri.parse(it.getStringExtra(MainActivity.IMG_GIRL));

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);

                    mContentView.setImageViewBitmap(R.id.img_girl_notify, bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            mContentView.setTextViewText(R.id.tv_male_name_notify, it.getStringExtra(MainActivity.TV_MALE));
            mContentView.setTextViewText(R.id.tv_female_name_notify, it.getStringExtra(MainActivity.TV_FEMALE));
            mContentView.setTextViewText(R.id.tv_time_notify, (Math.abs(getCurrentTimeFromCurrentCalendar()-mTime))/86400000+"");

            mNotifyManager.notify(NOTIFY_ID, mNotify.build());
        }
        return START_NOT_STICKY;
    }

    private long getCurrentTimeFromCurrentCalendar() {
        long cal = Calendar.getInstance().getTimeInMillis();
        return cal;
    }

    public void createNotification() {
        mContentView = new RemoteViews(getPackageName(), R.layout.notifycation);
//        mContentView.setImageViewResource(R.id.img_boy_notify, R.drawable.boy);
//        mContentView.setImageViewResource(R.id.img_girl_notify, R.drawable.girl);
        mContentView.setTextViewText(R.id.tv_time_notify, 0 + "");

        mNotify = new NotificationCompat.Builder(this);
        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notifyIntent = new Intent(getApplicationContext(), MainActivity.class);

        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mNotify.setOngoing(true);
        mNotify.setContentIntent(pendingIntent);
        mNotify.setAutoCancel(true);
        mNotify.setSmallIcon(R.drawable.ic_love_big);
        mNotify.setCustomContentView(mContentView);
        mNotify.setVisibility(Notification.VISIBILITY_PUBLIC);
        mNotifyManager.notify(NOTIFY_ID, mNotify.build());
    }

//    public static Bitmap StringToBitMap(String encodedString) {
//        try {
//            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            return bitmap;
//        } catch (Exception e) {
//            e.getMessage();
//            return null;
//        }
//    }

    public String BimapToString(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }
}
