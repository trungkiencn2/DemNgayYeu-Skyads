package com.example.hp6300pro.demngayyeu.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.hp6300pro.demngayyeu.Lock;
import com.example.hp6300pro.demngayyeu.activity.MainActivity;

/**
 * Created by HP 6300 Pro on 1/10/2018.
 */

public class MyReceiver extends BroadcastReceiver { //abc
    private static int countPowerOff = 0;

    public MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            context.startService(new Intent(context, LockService.class));
//            Intent it = new Intent(context, LockService.class);
//            it.setAction("com.truiton.foregroundservice.action.startforeground");
//            context.startService(it);
            countPowerOff++;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            context.stopService(new Intent(context, LockService.class));
//            Intent it = new Intent(context, LockService.class);
//            it.setAction("com.truiton.foregroundservice.action.stopforeground");
//            context.startService(it);
            countPowerOff++;
        } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            if (countPowerOff > 2) {
                countPowerOff = 0;
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
        }
    }
}
