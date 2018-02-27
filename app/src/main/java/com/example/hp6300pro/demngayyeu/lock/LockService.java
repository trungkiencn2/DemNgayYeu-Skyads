package com.example.hp6300pro.demngayyeu.lock;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hp6300pro.demngayyeu.Lock;
import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.activity.MainActivity;
import com.example.hp6300pro.demngayyeu.utils.Utils;

import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HP 6300 Pro on 1/10/2018.
 */

public class LockService extends Service{

    private WindowManager mWindowManager;
    private MyGroupView mViewIcon;
    private WindowManager.LayoutParams mIconViewParam;

    private CircleImageView mImgBoy;
    private CircleImageView mImgGirl;
    private ImageView mImgBg;
    private TextView mTvBottomDay, mTvCountDay, mTvDay, mTvSlideToUnlock;
    private SeekBar mSeekbar;

    private long timeStart;
    private long hieuThoiGian;
    private Typeface tf;

    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showView();
        return START_STICKY;
    }

    private void showView() {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mViewIcon = new MyGroupView(this);
        initView();
        addInfo();
        setFont();
        mIconViewParam = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);
        mWindowManager.addView(mViewIcon, mIconViewParam);
    }

    private void initView() {
        View view = View.inflate(this, R.layout.lockscreen, mViewIcon);
        mImgBoy = (CircleImageView) view.findViewById(R.id.img_boy_lock);
        mImgGirl = (CircleImageView) view.findViewById(R.id.img_girl_lock);
        mImgBg = (ImageView) view.findViewById(R.id.img_bg_lock);
        mTvBottomDay = (TextView) view.findViewById(R.id.tv_bottom_day_lock);
        mSeekbar = (SeekBar) view.findViewById(R.id.seekbar_lock);
        mTvCountDay = (TextView) view.findViewById(R.id.tv_count_day_lock);
        mTvDay = (TextView) view.findViewById(R.id.tv_day_lock);
        mTvSlideToUnlock = (TextView) view.findViewById(R.id.tv_slide_to_unlock);
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() > 50) {
                    mWindowManager.removeView(mViewIcon);
                } else {
                    seekBar.setProgress(0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress>50){

                }
            }
        });
    }

    private void addInfo() {
        String fileBoy = MainActivity.readFromFile(Utils.FILE_BOY, this);
        String fileGirl = MainActivity.readFromFile(Utils.FILE_GIRL, this);
        String fileBg = MainActivity.readFromFile(Utils.FILE_BG, this);
        timeStart = Long.parseLong(MainActivity.readFromFile(Utils.FILE_TIME_START, this));
        mImgBoy.setImageURI(Uri.parse(fileBoy));
        mImgGirl.setImageURI(Uri.parse(fileGirl));

        if(MainActivity.readFromFile(MainActivity.FILE_CONFIG, this).equals(MainActivity.PUT_INTEGER)){
            Bitmap bitmap = bitmapFromAssets(fileBg);
            mImgBg.setBackground(new BitmapDrawable(getResources(), MainActivity.scaleBitmap(bitmap, 600, 600)));
        }else {
            mImgBg.setImageURI(Uri.parse(fileBg));
        }

        mImgBg.setImageURI(Uri.parse(fileBg));
        Calendar mCa = Calendar.getInstance();
        mTvBottomDay.setText(mCa.get(Calendar.DAY_OF_MONTH) + "-" + (mCa.get(Calendar.MONTH)+1) + "-" + mCa.get(Calendar.YEAR));
        updateTime();
    }

    private void updateTime() {
        mHandler.postDelayed(countTime, 1000);
    }

    private Runnable countTime = new Runnable() {
        @Override
        public void run() {
            hieuThoiGian = (MainActivity.getCurrentTimeFromCurrentCalendar() - timeStart)/1000/86400;
            mTvCountDay.setText(hieuThoiGian + "");
            updateTime();
        }
    };

    private void setFont(){
        tf = Typeface.createFromAsset(getAssets(), "fonts/FiolexGirlVH.ttf");
        mTvCountDay.setTypeface(tf);
        mTvBottomDay.setTypeface(tf);
        mTvDay.setTypeface(tf);
        mTvSlideToUnlock.setTypeface(tf);
    }

    private Bitmap bitmapFromAssets(String path) {
        InputStream is = null;
        try {
            is = getAssets().open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }
}