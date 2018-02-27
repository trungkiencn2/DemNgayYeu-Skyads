package com.example.hp6300pro.demngayyeu.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.activity.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import me.itangqi.waveloadingview.WaveLoadingView;

/**
 * Created by HP 6300 Pro on 12/6/2017.
 */

public class FloatingViewService extends Service implements View.OnClickListener {

    private WindowManager mWindowManager;
    private View mFloatingView;
    private CircleImageView mImgBoyExpand, mImgGirlExpand;
    private WaveLoadingView mWaveView;
    private TextView mTvYear, mTvMonth, mTvDay, mTvHour, mTvMinute, mTvSecond, mTvMale, mTvFemale, mTvCountDay, mTvDayStart, mTvDaysExpand;
    private TextView mTvYearStatic, mTvMonthStatic, mTvDayStatic, mTvHourStatic, mTvMinuteStatic, mTvSecondStatic, mTvCountDayExpand;
    private ImageView mImgClockOrWave;
    private LinearLayout mLinearClock;
    private FrameLayout mFrameWave;
    private long mTime;
    private String mMale, mFemale;
    private Handler mHandler = new Handler();
    private RemoteViews mContentView;
    private NotificationCompat.Builder mNotify;
    private NotificationManager mNotifyManager;
    public static final int NOTIFY_ID = 1;
    private long hieuThoiGian;
    private String mPathFont;
    private IBinder myBinder = new MyBinder();

    public static final String CLOCK = "CLOCK";
    public static final String WAVE = "WAVE";
    public static String CLOCK_OR_WAVE = WAVE;

    public FloatingViewService() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_clock_or_wave:
                if(CLOCK_OR_WAVE.equals(WAVE)){
                    CLOCK_OR_WAVE = CLOCK;
                    mImgClockOrWave.setImageResource(R.drawable.clock_white);
                    mFrameWave.setVisibility(View.GONE);
                    mLinearClock.setVisibility(View.VISIBLE);
                }else if(CLOCK_OR_WAVE.equals(CLOCK)){
                    CLOCK_OR_WAVE = WAVE;
                    mImgClockOrWave.setImageResource(R.drawable.heart_white);
                    mLinearClock.setVisibility(View.GONE);
                    mFrameWave.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public class MyBinder extends Binder{
        public FloatingViewService getService(){
            return FloatingViewService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);
        mImgBoyExpand = (CircleImageView) mFloatingView.findViewById(R.id.img_boy_expand);
        mImgGirlExpand = (CircleImageView) mFloatingView.findViewById(R.id.img_girl_expand);
        mTvYear = (TextView) mFloatingView.findViewById(R.id.tv_year_expand);
        mTvMonth = (TextView) mFloatingView.findViewById(R.id.tv_month_expand);
        mTvDay = (TextView) mFloatingView.findViewById(R.id.tv_day_expand);
        mTvHour = (TextView) mFloatingView.findViewById(R.id.tv_hour_expand);
        mTvMinute = (TextView) mFloatingView.findViewById(R.id.tv_minute_expand);
        mTvSecond = (TextView) mFloatingView.findViewById(R.id.tv_second_expand);
        mTvMale = (TextView) mFloatingView.findViewById(R.id.tv_male_expand);
        mTvFemale = (TextView) mFloatingView.findViewById(R.id.tv_female_expand);
        mTvCountDay = (TextView) mFloatingView.findViewById(R.id.tv_count_day_floating);
        mTvDayStart = (TextView) mFloatingView.findViewById(R.id.tv_day_start_expand);
        mTvCountDayExpand = (TextView) mFloatingView.findViewById(R.id.tv_count_day_expand);
        mTvDaysExpand = (TextView) mFloatingView.findViewById(R.id.tv_days_expand);

        mTvYearStatic = (TextView) mFloatingView.findViewById(R.id.tv_year_expand_static);
        mTvMonthStatic = (TextView) mFloatingView.findViewById(R.id.tv_month_expand_static);
        mTvDayStatic = (TextView) mFloatingView.findViewById(R.id.tv_day_expand_static);
        mTvHourStatic = (TextView) mFloatingView.findViewById(R.id.tv_hour_expand_static);
        mTvMinuteStatic = (TextView) mFloatingView.findViewById(R.id.tv_minute_expand_static);
        mTvSecondStatic = (TextView) mFloatingView.findViewById(R.id.tv_second_expand_static);

        mImgClockOrWave = (ImageView) mFloatingView.findViewById(R.id.img_clock_or_wave);
        mWaveView = (WaveLoadingView) mFloatingView.findViewById(R.id.wave_view_expand);
        mLinearClock = (LinearLayout) mFloatingView.findViewById(R.id.linear_clock_expand);
        mFrameWave = (FrameLayout) mFloatingView.findViewById(R.id.frame_wave);

        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);

        final View collapsedView = mFloatingView.findViewById(R.id.collapse_view);
        final View expandedView = mFloatingView.findViewById(R.id.linear_expand);

        ImageView closeButtonCollapse = (ImageView) mFloatingView.findViewById(R.id.close_btn);
        closeButtonCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close the service and remove the from from the window
//                Intent intent = new Intent(FloatingViewService.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                stopSelf();
            }
        });

        ImageView closeButtonExpand = (ImageView) mFloatingView.findViewById(R.id.img_close_expand);
        closeButtonExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapsedView.setVisibility(View.VISIBLE);
                expandedView.setVisibility(View.GONE);
            }
        });

        ImageView openButton = (ImageView) mFloatingView.findViewById(R.id.img_open_expand);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open the application  click.
                Intent intent = new Intent(FloatingViewService.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                stopSelf();
            }
        });

        mFloatingView.findViewById(R.id.root_container).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);

                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                //When user clicks on the image view of the collapsed layout,
                                //visibility of the collapsed layout will be changed to "View.GONE"
                                //and expanded view will become visible.
                                collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public int onStartCommand(Intent it, int flags, int startId) {
        mTime = it.getLongExtra(MainActivity.TIME_START, 0);
        mPathFont = it.getStringExtra(MainActivity.PATH_FONT);
//        mImgBoyExpand.setImageBitmap(StringToBitMap(it.getStringExtra(MainActivity.IMG_BOY)));
//        mImgGirlExpand.setImageBitmap(StringToBitMap(it.getStringExtra(MainActivity.IMG_GIRL)));

        if (it.getStringExtra(MainActivity.IMG_BOY) != null) {
            Uri fileUri = Uri.parse(it.getStringExtra(MainActivity.IMG_BOY));

            mImgBoyExpand.setImageURI(fileUri);
        }

        if (it.getStringExtra(MainActivity.IMG_GIRL) != null) {
            Uri fileUri = Uri.parse(it.getStringExtra(MainActivity.IMG_GIRL));

            mImgGirlExpand.setImageURI(fileUri);

        }

        mTvMale.setText(it.getStringExtra(MainActivity.TV_MALE));
        mTvFemale.setText(it.getStringExtra(MainActivity.TV_FEMALE));
        mImgClockOrWave.setOnClickListener(this);
        setFont(mPathFont);
        if(mTime != 0){
            updateTime();
        }
        return START_STICKY;
    }

    private void setFont(String mPathFont) {
        Typeface tf = Typeface.createFromAsset(getAssets(), mPathFont);
        mTvYear.setTypeface(tf);
        mTvYearStatic.setTypeface(tf);
        mTvMonth.setTypeface(tf);
        mTvMonthStatic.setTypeface(tf);
        mTvDay.setTypeface(tf);
        mTvDayStatic.setTypeface(tf);
        mTvHour.setTypeface(tf);
        mTvHourStatic.setTypeface(tf);
        mTvMinute.setTypeface(tf);
        mTvMinuteStatic.setTypeface(tf);
        mTvSecond.setTypeface(tf);
        mTvSecondStatic.setTypeface(tf);
        mTvCountDay.setTypeface(tf);
        mTvMale.setTypeface(tf);
        mTvFemale.setTypeface(tf);
        mTvDayStart.setTypeface(tf);
        mTvCountDayExpand.setTypeface(tf);
        mTvDaysExpand.setTypeface(tf);
    }

    private long getCurrentTimeFromCurrentCalendar() {
        long cal = Calendar.getInstance().getTimeInMillis();
        return cal;
    }

    private void updateTime() {
        mHandler.postDelayed(countTime, 1000);
    }

    private Runnable countTime = new Runnable() {
        @Override
        public void run() {
            hieuThoiGian = (getCurrentTimeFromCurrentCalendar() - mTime)/1000;
            mTvSecond.setText(hieuThoiGian % 60 + "");
            mTvMinute.setText(hieuThoiGian / 60 + "");
            if (Integer.parseInt(mTvMinute.getText().toString()) > 59) {
                mTvMinute.setText(Integer.parseInt(mTvMinute.getText().toString()) % 60 + "");
            }
            mTvHour.setText(hieuThoiGian / 3600 + "");
            if (Integer.parseInt(mTvHour.getText().toString()) > 23) {
                mTvHour.setText(Integer.parseInt(mTvHour.getText().toString()) % 24 + "");
            }
            mTvDay.setText(hieuThoiGian / 86400 + "");
            if (Integer.parseInt(mTvDay.getText().toString()) > 29) {
                mTvDay.setText(Integer.parseInt(mTvDay.getText().toString()) % 30 + "");
            }
            mTvMonth.setText(hieuThoiGian / 2592000 + "");
            if (Integer.parseInt(mTvMonth.getText().toString()) > 11) {
                mTvMonth.setText(Integer.parseInt(mTvMonth.getText().toString()) % 12 + "");
            }
            mTvYear.setText(hieuThoiGian / 31104000 + "");
            if (hieuThoiGian / 86400 <= 7) {
                mWaveView.setProgressValue(15);
            } else if (hieuThoiGian / 86400 <= 14) {
                mWaveView.setProgressValue(20);
            } else if (hieuThoiGian / 86400 <= 30) {
                mWaveView.setProgressValue(35);
            } else if (hieuThoiGian / 86400 <= 50) {
                mWaveView.setProgressValue(50);
            } else if (hieuThoiGian / 86400 <= 80) {
                mWaveView.setProgressValue(70);
            } else {
                mWaveView.setProgressValue(75);
            }
            mTvCountDay.setText(hieuThoiGian/86400+"");
            mTvCountDayExpand.setText(hieuThoiGian/86400+"");
            updateTime();
        }
    };

    private boolean isViewCollapsed() {
        return mFloatingView == null || mFloatingView.findViewById(R.id.collapse_view).getVisibility() == View.VISIBLE;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public String BimapToString(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

}
