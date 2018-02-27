package com.example.hp6300pro.demngayyeu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.lock.MyReceiver;

public class LockScreenActivity extends Activity {

//        @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀화면도 없이 전체화면으로 실행
//        getWindow().addFlags( WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
//                WindowManager.LayoutParams.FLAG_FULLSCREEN |
//                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
//                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
//                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
//        setContentView(R.layout.activity_lock_screen);
//        SwipeButton mSwipeButton = (SwipeButton) findViewById(R.id.my_swipe_button);
//
//        SwipeButtonCustomItems swipeButtonSettings = new SwipeButtonCustomItems() {
//            @Override
//            public void onSwipeConfirm() {
//                Log.d("NEW_STUFF", "New swipe confirm callback");
//            }
//        };
//
//        swipeButtonSettings
//                .setButtonPressText(">> NEW TEXT! >>")
//                .setGradientColor1(0xFF888888)
//                .setGradientColor2(0xFF666666)
//                .setGradientColor2Width(60)
//                .setGradientColor3(0xFF333333)
//                .setPostConfirmationColor(0xFF888888)
//                .setActionConfirmDistanceFraction(0.7)
//                .setActionConfirmText("Action Confirmed");
//
//        if (mSwipeButton != null) {
//            mSwipeButton.setSwipeButtonCustomItems(swipeButtonSettings);
//
////            finish();
//        }
//    }
        private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, filter);
//        finish();
    }

    @Override
    protected void onDestroy()
    {
//        if (myReceiver != null)
//        {
//            unregisterReceiver(myReceiver);
//            myReceiver = null;
//        }
        super.onDestroy();
    }



    public void onBackPressed() {
        return; // Back키 무력화
    }

}
