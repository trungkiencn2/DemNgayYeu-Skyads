package com.example.hp6300pro.demngayyeu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hp6300pro.demngayyeu.swipe_button.SwipeButton;
import com.example.hp6300pro.demngayyeu.swipe_button.SwipeButtonCustomItems;

public class Lock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀화면도 없이 전체화면으로 실행
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock);
        SwipeButton mSwipeButton = (SwipeButton) findViewById(R.id.my_swipe_button);

        SwipeButtonCustomItems swipeButtonSettings = new SwipeButtonCustomItems() {
            @Override
            public void onSwipeConfirm() {
                Log.d("NEW_STUFF", "New swipe confirm callback");
            }
        };

        swipeButtonSettings
                .setButtonPressText(">> NEW TEXT! >>")
                .setGradientColor1(0xFF888888)
                .setGradientColor2(0xFF666666)
                .setGradientColor2Width(60)
                .setGradientColor3(0xFF333333)
                .setPostConfirmationColor(0xFF888888)
                .setActionConfirmDistanceFraction(0.7)
                .setActionConfirmText("Action Confirmed");

        if (mSwipeButton != null) {
            mSwipeButton.setSwipeButtonCustomItems(swipeButtonSettings);
            mSwipeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
//            finish();
        }
    }
}
