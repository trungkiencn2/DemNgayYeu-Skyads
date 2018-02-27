package com.example.hp6300pro.demngayyeu.lock;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by KienBeu on 2/27/2018.
 */

public class Sv extends Service {

    private MyReceiver myReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.setPriority(999);
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, filter);
        return START_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.myReceiver != null) {
            unregisterReceiver(this.myReceiver);
        }
    }
}
