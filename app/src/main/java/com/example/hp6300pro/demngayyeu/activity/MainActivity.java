package com.example.hp6300pro.demngayyeu.activity;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.ApplicationErrorReport;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.adapter.ListFontAdapter;
import com.example.hp6300pro.demngayyeu.adapter.RecycleFunctionAdapter;
import com.example.hp6300pro.demngayyeu.adapter.RecyclerItemClickListener;
import com.example.hp6300pro.demngayyeu.ads.MyAdmobController;
import com.example.hp6300pro.demngayyeu.ads.MyBaseMainActivity;
import com.example.hp6300pro.demngayyeu.database.DatabaseHandler;
import com.example.hp6300pro.demngayyeu.hdp_utils.DecodeIcon;
import com.example.hp6300pro.demngayyeu.lock.MyReceiver;
import com.example.hp6300pro.demngayyeu.lock.Sv;
import com.example.hp6300pro.demngayyeu.model.Function;
import com.example.hp6300pro.demngayyeu.receiver.AlarmNotifyReceiver;
import com.example.hp6300pro.demngayyeu.service.FloatingViewService;
import com.example.hp6300pro.demngayyeu.service.NotifyService;
import com.example.hp6300pro.demngayyeu.utils.AlertDialogUtils;
import com.example.hp6300pro.demngayyeu.utils.Constants;
import com.example.hp6300pro.demngayyeu.utils.Idelegate;
import com.example.hp6300pro.demngayyeu.utils.Utils;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.kobakei.ratethisapp.RateThisApp;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends MyBaseMainActivity implements View.OnClickListener {

    public static final int IMAGE_GALLERY_REQUEST = 10;
    public static final String CHUP_ANH_STRING = "CHUP_ANH_STRING";
    public static final String PUT_DIARY = "PUT_DIARY";
    public static final String CHUP_ANH_STRING_FOR_CHANGE = "CHUP_ANH_STRING_FOR_CHANGE";
    public static final String PATH_FONT = "PATH_FONT"; //
    public static final String PUT_TIME_TO_ALARM = "PUT_TIME_TO_ALARM";
    public static final String CAMERA_BACK_FOR_CHANGE = "CAMERA_BACK_FOR_CHANGE";
    public static final String CAMERA_BACK_FOR_MAIN = "CAMERA_BACK_FOR_MAIN";
    public static String CHECK_CAMERA_FOR_WHERE;
    public static final int MAIN_TO_CAMERA = 11;
    public static final int CHANGE_TO_CAMERA = 12;
    public static final int BOY = 1;
    public static final int GIRL = 0;
    public static int CHOOSE_IMAGE;
    public static final int NOTIFY_ID = 1;
    public static final String PUT_TIME_START = "PUT_TIME_START";
    public static final String PUT_INTEGER = "PUT_INTEGER";
    public static final String PUT_URI = "PUT_URI";
    public static final String FILE_CONFIG = "FILE_CONFIG";
    public static final int CHUP_ANH = 5;
    public static final int CHUP_ANH_FOR_CHANGE = 6;
    public static String PUT_TYPE;
    public static final int RESULT_FROM_CHANGBG = 4;
    public static final String RESULT_FROM_CHANGBG_STRING = "RESULT_FROM_CHANGBG_STRING";
    public static final int CHANGE_BG_BACK = 3;
    public static final int GALLERY_REQUEST = 2;
    public static final int CROP_REQUEST = 1;
    public static final String IMG_BOY = "IMG_BOY";
    public static final String IMG_GIRL = "IMG_GIRL";
    public static final String TV_MALE = "TV_MALE";
    public static final String TV_FEMALE = "TV_FEMALE";
    public static final String TV_SECOND = "TV_SECOND";
    public static final String TV_MINUTE = "TV_MINUTE";
    public static final String TV_HOUR = "TV_HOUR";
    public static final String TV_DAY = "TV_DAY";
    public static final String TV_MONTH = "TV_MONTH";
    public static final String TV_YEAR = "TV_YEAR";
    public static final String TV_TITLE_ABOVE = "TV_TITLE_ABOVE";
    public static final String TV_TITLE_BOTTOM = "TV_TITLE_BOTTOM";
    public static final String DATE_START_LOVE = "DATE_START_LOVE";
    public static final String TIME_START = "TIME_START";
    public static final String LOVE = "LOVE";
    public static final String FAREWELL = "FAREWELL";
    public static String STATE = FAREWELL;
    public static final String TITLE_ABOVE = "TITLE_ABOVE";
    public static final String TITLE_BOTTOM = "TITLE_BOTTOM";
    public static String TITLE;
    public static final String BOYDATA = "BOYDATA";
    public static final String GIRLDATA = "GIRLDATA";
    public static final String BOYDATA_CHUPANH = "BOYDATA_CHUPANH";
    public static final String GIRLDATA_CHUPANH = "GIRLDATA_CHUPANH";
    public static final String BGDATA = "BGDATA";
    public static final String COLOR_WAVE = "COLOR_WAVE";
    public static final String COLOR_BG_WAVE = "COLOR_BG_WAVE";
    public static final String CHOOSE_BOY_GALLERY = "CHOOSE_BOY_GALLERY";
    public static final String CHOOSE_BOY_CAMERA = "CHOOSE_BOY_CAMERA";
    public static String CHOOSE_BOY_TYPE = CHOOSE_BOY_GALLERY;
    public static final String CHOOSE_GIRL_GALLERY = "CHOOSE_GIRL_GALLERY";
    public static final String CHOOSE_GIRL_CAMERA = "CHOOSE_GIRL_CAMERA";
    public static String CHOOSE_GIRL_TYPE = CHOOSE_GIRL_GALLERY;
    public static boolean BOY_CO_DU_LIEU = false;
    public static boolean GIRL_CO_DU_LIEU = false;
    public static final String LOCK_SCREEN = "LOCK_SCREEN";

    private WaveLoadingView mWave;
    private FrameLayout mFrameBg;
    private LinearLayout mLinearAni;
    private Toolbar mToolBar;
    private CircleImageView mImgBoy, mImgGirl;
    private ImageView mImgHeart1, mImgHeart2, mImgHeart3, mImgHeart4, mImgHeart5, mImgHeart6, mImgHeart7, mImgHeart8, mImgDiary, mImgSetting;
    private TextView mTvYear, mTvMonth, mTvDay, mTvHour, mTvMinute, mTvSecond, mTvLoveForever, mTvTitleBottom, mTvMale, mTvFemale, mTvTimeStartLove, mTvDayInWave, mTvDayInMain;
    private TextView mTvYearStatic, mTvMonthStatic, mTvDayStatic, mTvHourStatic, mTvMinuteStatic, mTvSecondStatic, mTvTitleFunction;

    private ArrayList<Function> mListFunction;
    private ArrayList<Integer> mListBg;
    private ArrayList<Integer> mListFont;
    public static HashMap<String, Long> hashMapTime = new HashMap<>();
    private long hieuThoiGian;
    private Typeface tf;
    public static Intent itForNotify;

    private TranslateAnimation translateAnimation1;
    private TranslateAnimation translateAnimation2;
    private TranslateAnimation translateAnimation3;
    private TranslateAnimation translateAnimation4;
    private TranslateAnimation translateAnimation5;
    private TranslateAnimation translateAnimation6;
    private TranslateAnimation translateAnimation7;
    private TranslateAnimation translateAnimation8;

    private Handler mHandler = new Handler();
    private android.graphics.Camera mCamera;
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    public static SharedPreferences.Editor editor;

    File file;
    Uri uri;
    Intent CamIntent, GalIntent, CropIntent;
    final int RequestPermissionCode = 1;
    DisplayMetrics displayMetrics;
    int width, height;
    public static final int CROP_REQUEST_CHUPANH = 53;

    public static long getDeltaDays(Calendar d1Calendar, Calendar d2Calendar) {
        long time = Math.abs(d1Calendar.getTimeInMillis() - d2Calendar.getTimeInMillis());
        return time;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addAnimation();
        loadFromSave();
        updateTime();
        startNotifyService(); //abc

        RateThisApp.Config config = new RateThisApp.Config(1, 5);
        config.setTitle(R.string.my_own_title);
        config.setMessage(R.string.my_own_message);
        config.setYesButtonText(R.string.my_own_rate);
        config.setNoButtonText(R.string.my_own_thanks);
        config.setCancelButtonText(R.string.my_own_cancel);

        String urlRate = "https://play.google.com/store/apps/details?id=" + getPackageName();
        config.setUrl(urlRate);
        RateThisApp.init(config);
        RateThisApp.onCreate(this);

        try {
            RateThisApp.showRateDialogIfNeeded(this);
        } catch (Exception e) {

        }
    }

    private long getHieuTimeToSangMai() {

        Calendar mCaNow = Calendar.getInstance();
        Calendar mCaTomorrow = Calendar.getInstance();
        mCaTomorrow.set(mCaNow.get(Calendar.YEAR), mCaNow.get(Calendar.MONTH), mCaNow.get(Calendar.DAY_OF_MONTH) + 1, 7, 0, 0);
        return mCaTomorrow.getTimeInMillis() - mCaNow.getTimeInMillis();

    }

    private void startAlarm(long mNewTime) {
        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        myIntent = new Intent(MainActivity.this, AlarmNotifyReceiver.class);
        myIntent.putExtra(TIME_START, prefs.getLong(TIME_START, 0));

        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        if (mNewTime == prefs.getLong(TIME_START, 0)) {

            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + getHieuTimeToSangMai(), 86400000, pendingIntent);

        } else {
            if (pendingIntent != null) {
                manager.cancel(pendingIntent);
                myIntent.putExtra(TIME_START, mNewTime);
                pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + getHieuTimeToSangMai(), 86400000, pendingIntent);
            }

        }
    }

    private Date stringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static long getCurrentTimeFromCurrentCalendar() {
        long cal = Calendar.getInstance().getTimeInMillis();
        return cal;
    }

    public static long getTimeFromCalendar(Calendar c) {
        return c.getTimeInMillis();
    }

    private void initView() {
        mFrameBg = (FrameLayout) findViewById(R.id.linear_bg);
        mTvMale = (TextView) findViewById(R.id.tv_male);
        mTvFemale = (TextView) findViewById(R.id.tv_female);
        mImgBoy = (CircleImageView) findViewById(R.id.img_boy);
        mImgGirl = (CircleImageView) findViewById(R.id.img_girl);
        mImgHeart1 = (ImageView) findViewById(R.id.img_heart1);
        mImgHeart2 = (ImageView) findViewById(R.id.img_heart2);
        mImgHeart3 = (ImageView) findViewById(R.id.img_heart3);
        mImgHeart4 = (ImageView) findViewById(R.id.img_heart4);
        mImgHeart5 = (ImageView) findViewById(R.id.img_heart5);
        mImgHeart6 = (ImageView) findViewById(R.id.img_heart6);
        mImgHeart7 = (ImageView) findViewById(R.id.img_heart7);
        mImgHeart8 = (ImageView) findViewById(R.id.img_heart8);
        mImgDiary = (ImageView) findViewById(R.id.img_diary_main);
        mImgSetting = (ImageView) findViewById(R.id.img_setting_main);
        mTvYear = (TextView) findViewById(R.id.tv1);
        mTvMonth = (TextView) findViewById(R.id.tv2);
        mTvDay = (TextView) findViewById(R.id.tv3);
        mTvHour = (TextView) findViewById(R.id.tv4);
        mTvMinute = (TextView) findViewById(R.id.tv5);
        mTvSecond = (TextView) findViewById(R.id.tv6);
        mTvLoveForever = (TextView) findViewById(R.id.tv_love_forever);
        mTvTitleBottom = (TextView) findViewById(R.id.tv_title_bottom);
        mTvTimeStartLove = (TextView) findViewById(R.id.tv_time_startlove);
        mTvDayInWave = (TextView) findViewById(R.id.tv_days_in_wave);
        mTvDayInMain = (TextView) findViewById(R.id.tv_days);
        mTvYearStatic = (TextView) findViewById(R.id.tv_year_static);
        mTvMonthStatic = (TextView) findViewById(R.id.tv_month_static);
        mTvDayStatic = (TextView) findViewById(R.id.tv_day_static);
        mTvHourStatic = (TextView) findViewById(R.id.tv_hour_static);
        mTvMinuteStatic = (TextView) findViewById(R.id.tv_minute_static);
        mTvSecondStatic = (TextView) findViewById(R.id.tv_second_static);
        mWave = (WaveLoadingView) findViewById(R.id.wave_view);

        mImgBoy.setOnClickListener(this);
        mImgGirl.setOnClickListener(this);
        mTvMale.setOnClickListener(this);
        mTvFemale.setOnClickListener(this);
        mWave.setOnClickListener(this);
        mImgDiary.setOnClickListener(this);
        mImgSetting.setOnClickListener(this);
        mDb = new DatabaseHandler(this);

        if (mDb.getListFunction().size() == 0) {
            mDb.addFunction(new Function(R.drawable.ic_change_background, getString(R.string.change_background)));
            mDb.addFunction(new Function(R.drawable.ic_change_day, getString(R.string.change_love_date)));
            mDb.addFunction(new Function(R.drawable.ic_title, getString(R.string.change_above_title)));
            mDb.addFunction(new Function(R.drawable.ic_title, getString(R.string.change_bottom_title)));
            mDb.addFunction(new Function(R.drawable.ic_floating, getString(R.string.set_quick_keys)));
            mDb.addFunction(new Function(R.drawable.ic_font, getString(R.string.set_font)));
            mDb.addFunction(new Function(R.drawable.ic_color, getString(R.string.change_color_wave)));
            mDb.addFunction(new Function(R.drawable.ic_color, getString(R.string.change_bg_color_wave)));
            mDb.addFunction(new Function(R.drawable.ic_rate, getString(R.string.rate_us)));
            mDb.addFunction(new Function(R.drawable.ic_share, getString(R.string.share)));
            mDb.addFunction(new Function(R.drawable.ic_lockscreen, getString(R.string.set_lock_screen)));
        }
    }

    private int colorSelected, colorBgSelected;

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    private void showColorPickerDialog() {
        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        ColorPickerDialogBuilder
                .with(this)
                .setTitle(getString(R.string.choose_color))
                .initialColor(prefs.getInt(COLOR_WAVE, mWave.getWaveColor()))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        colorSelected = adjustAlpha(selectedColor, 0.6f);
                    }
                })
                .setPositiveButton(getString(R.string.ok), new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) { //abc
                        colorSelected = adjustAlpha(selectedColor, 0.6f);
                        mWave.setWaveColor(colorSelected);
                        editor.putInt(COLOR_WAVE, colorSelected);
                        editor.apply();

                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    private void showColorPickerDialogForBg() {
        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        ColorPickerDialogBuilder
                .with(this)
                .setTitle(getString(R.string.choose_color))
                .initialColor(prefs.getInt(COLOR_BG_WAVE, mWave.getWaveBgColor()))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        colorBgSelected = adjustAlpha(selectedColor, 0.6f);
                    }
                })
                .setPositiveButton(getString(R.string.ok), new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) { //abc
                        colorBgSelected = (selectedColor);
                        mWave.setWaveBgColor(colorBgSelected);
                        editor.putInt(COLOR_BG_WAVE, colorBgSelected);
                        editor.apply();

                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    private void updateTime() {
        mHandler.postDelayed(countTime, 1000);
    }

    private Runnable countTime = new Runnable() {
        @Override
        public void run() {
            final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            hieuThoiGian = (getCurrentTimeFromCurrentCalendar() - prefs.getLong(TIME_START, 0)) / 1000;
            if (hieuThoiGian > 0 && prefs.getLong(TIME_START, 0) != 0) {
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
                mTvDayInWave.setText(String.valueOf(hieuThoiGian / 86400));
                if (hieuThoiGian / 86400 <= 7) {
                    mWave.setProgressValue(15);
                } else if (hieuThoiGian / 86400 <= 14) {
                    mWave.setProgressValue(20);
                } else if (hieuThoiGian / 86400 <= 30) {
                    mWave.setProgressValue(35);
                } else if (hieuThoiGian / 86400 <= 50) {
                    mWave.setProgressValue(50);
                } else if (hieuThoiGian / 86400 <= 80) {
                    mWave.setProgressValue(70);
                } else {
                    mWave.setProgressValue(75);
                }
            } else if (hieuThoiGian <= 0) {
                setTimeToDefault();
            }
            updateTime();
        }
    };

    private void setTimeToDefault() {
        mTvYear.setText(0 + "");
        mTvMonth.setText(0 + "");
        mTvDay.setText(0 + "");
        mTvHour.setText(0 + "");
        mTvMinute.setText(0 + "");
        mTvSecond.setText(0 + "");
        mTvDayInWave.setText(0 + "");
        mWave.setProgressValue(2);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void startNotifyService() {
        if (isMyServiceRunning(NotifyService.class) && itForNotify != null) {
            stopService(itForNotify);
        }

        final SharedPreferences prefsN = getPreferences(MODE_PRIVATE);
        itForNotify = new Intent(MainActivity.this, NotifyService.class);

        String stringUriBoy = prefsN.getString(IMG_BOY, null);
        String stringUriGirl = prefsN.getString(IMG_GIRL, null);

        itForNotify.putExtra(IMG_BOY, stringUriBoy);
        itForNotify.putExtra(IMG_GIRL, stringUriGirl);


        itForNotify.putExtra(TV_MALE, mTvMale.getText().toString());
        itForNotify.putExtra(TV_FEMALE, mTvFemale.getText().toString());
        itForNotify.putExtra(TIME_START, prefsN.getLong(TIME_START, 0));
        startService(itForNotify);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loadFromSave() {
        editor = getPreferences(MODE_PRIVATE).edit();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        if (!prefs.getString(TV_MALE, "").equals("") && !prefs.getString(TV_FEMALE, "").equals("")) {
            mTvMale.setText(prefs.getString(TV_MALE, ""));
            mTvFemale.setText(prefs.getString(TV_FEMALE, ""));
        }
        mTvSecond.setText(prefs.getString(TV_SECOND, 0 + ""));
        mTvMinute.setText(prefs.getString(TV_MINUTE, 0 + ""));
        mTvHour.setText(prefs.getString(TV_HOUR, 0 + ""));
        mTvDay.setText(prefs.getString(TV_DAY, 0 + ""));
        mTvMonth.setText(prefs.getString(TV_MONTH, 0 + ""));
        mTvYear.setText(prefs.getString(TV_YEAR, 0 + ""));
        mTvLoveForever.setText(prefs.getString(TV_TITLE_ABOVE, getString(R.string.love_forever)));
        mTvTitleBottom.setText(prefs.getString(TV_TITLE_BOTTOM, getString(R.string.clock_love)));
        mTvTimeStartLove.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.YEAR));
        mWave.setWaveColor(prefs.getInt(COLOR_WAVE, 1));
        mWave.setWaveBgColor(prefs.getInt(COLOR_BG_WAVE, 1));

        if (StartActivity.START_OR_CONTINUE.equals(StartActivity.START_LOVE)) {
            hashMapTime.clear();
            hashMapTime.put(TIME_START, getIntent().getLongExtra(TIME_START, 0));
            if (hashMapTime.get(TIME_START) == 0) {
                hashMapTime.put(TIME_START, prefs.getLong(TIME_START, 0));
            }
            editor.putString(StartActivity.START_OR_CONTINUE, StartActivity.CONTINUE_LOVE);
            editor.putLong(TIME_START, hashMapTime.get(TIME_START));
            editor.apply();
        }

        PUT_TYPE = readFromFile(FILE_CONFIG, this);
        if (PUT_TYPE != null) {
            if (PUT_TYPE.equals(PUT_INTEGER)) {

                Bitmap b = bitmapFromAssets(prefs.getString(BGDATA, "anh_nen/bg_1.jpg"));

                mFrameBg.setBackground(new BitmapDrawable(getResources(), scaleBitmap(b, 600, 600)));

            } else if (PUT_TYPE.equals(PUT_URI)) {
                try {



                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(prefs.getString(BGDATA, null)));
                    mFrameBg.setBackground(new BitmapDrawable(getResources(), scaleBitmap(bitmap, 600, 600)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        if (prefs.getString(IMG_BOY, null) == null) {
            mImgBoy.setImageResource(R.drawable.boy);
        } else {
            Uri fileUri = Uri.parse(prefs.getString(IMG_BOY, null));
            mImgBoy.setImageURI(fileUri);
        }
        if (prefs.getString(IMG_GIRL, null) == null) {
            mImgGirl.setImageResource(R.drawable.girl);
        } else {
            Uri fileUri = Uri.parse(prefs.getString(IMG_GIRL, null));
            mImgGirl.setImageURI(fileUri);
        }


        String mPathFont = prefs.getString(PATH_FONT, Constants.DEFAULT_FONT);
        setFont(mPathFont);
        startAlarm(prefs.getLong(TIME_START, 0));
        putToSave();
    }

    private void clearAnimation() {
        if (translateAnimation1 != null && translateAnimation2 != null && translateAnimation3 != null && translateAnimation4 != null
                && translateAnimation5 != null && translateAnimation6 != null && translateAnimation7 != null && translateAnimation8 != null) {
            mImgHeart1.clearAnimation();
            mImgHeart2.clearAnimation();
            mImgHeart3.clearAnimation();
            mImgHeart4.clearAnimation();
            mImgHeart5.clearAnimation();
            mImgHeart6.clearAnimation();
            mImgHeart7.clearAnimation();
            mImgHeart8.clearAnimation();
        }
    }

    private void addAnimation() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clearAnimation();
                translateAnimation1 = new TranslateAnimation(0, 0, 0, -100);
                translateAnimation1.setDuration(1100);
                translateAnimation1.setFillAfter(true);
                mImgHeart1.setAnimation(translateAnimation1);
                translateAnimation2 = new TranslateAnimation(0, -100, 0, 0);
                translateAnimation2.setDuration(1100);
                translateAnimation2.setFillAfter(true);
                mImgHeart2.setAnimation(translateAnimation2);
                translateAnimation3 = new TranslateAnimation(0, 100, 0, 0);
                translateAnimation3.setDuration(1100);
                translateAnimation3.setFillAfter(true);
                mImgHeart3.setAnimation(translateAnimation3);
                translateAnimation4 = new TranslateAnimation(0, 0, 0, 100);
                translateAnimation4.setDuration(1100);
                translateAnimation4.setFillAfter(true);
                mImgHeart4.setAnimation(translateAnimation4);

                translateAnimation5 = new TranslateAnimation(0, 0, 0, -100);
                translateAnimation5.setDuration(1100);
                translateAnimation5.setFillAfter(true);
                mImgHeart5.setAnimation(translateAnimation5);
                translateAnimation6 = new TranslateAnimation(0, -100, 0, 0);
                translateAnimation6.setDuration(1100);
                translateAnimation6.setFillAfter(true);
                mImgHeart6.setAnimation(translateAnimation6);
                translateAnimation7 = new TranslateAnimation(0, 100, 0, 0);
                translateAnimation7.setDuration(1100);
                translateAnimation7.setFillAfter(true);
                mImgHeart7.setAnimation(translateAnimation7);
                translateAnimation8 = new TranslateAnimation(0, 0, 0, 100);
                translateAnimation8.setDuration(1100);
                translateAnimation8.setFillAfter(true);
                mImgHeart8.setAnimation(translateAnimation8);
                addAnimation();
            }
        }, 1200);
    }

    private void writeToFile(String data, String fileName, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(String fileName, Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
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

//    public String BimapToString(Bitmap image) {
//        Bitmap immagex = image;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
//        return imageEncoded;
//    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void putToSave() {
        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(TV_MALE, mTvMale.getText().toString());
        editor.putString(TV_FEMALE, mTvFemale.getText().toString());
        editor.putString(TV_SECOND, mTvSecond.getText().toString());
        editor.putString(TV_MINUTE, mTvMinute.getText().toString());
        editor.putString(TV_HOUR, mTvHour.getText().toString());
        editor.putString(TV_DAY, mTvDay.getText().toString());
        editor.putString(TV_MONTH, mTvMonth.getText().toString());
        editor.putString(TV_YEAR, mTvYear.getText().toString());
        editor.putString(TV_TITLE_ABOVE, mTvLoveForever.getText().toString());
        editor.putString(TV_TITLE_BOTTOM, mTvTitleBottom.getText().toString());
        if (!hashMapTime.isEmpty()) {
            editor.putLong(TIME_START, hashMapTime.get(TIME_START));
        }
        editor.apply();
    }

    Bitmap mBitmapShare;

    public void onImageGalleryClicked(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                uri = result.getUri();
                if (CHOOSE_IMAGE == BOY) {
                    BOY_CO_DU_LIEU = true;
                    CHOOSE_BOY_TYPE = CHOOSE_BOY_GALLERY;
                    editor.putString(CHOOSE_BOY_TYPE, CHOOSE_BOY_GALLERY);
                    editor.putString(IMG_BOY, uri.toString());
                    mImgBoy.setImageURI(uri);
                    writeToFile(uri.toString(), Utils.FILE_BOY, this);
                } else if (CHOOSE_IMAGE == GIRL) {
                    GIRL_CO_DU_LIEU = true;
                    CHOOSE_GIRL_TYPE = CHOOSE_GIRL_GALLERY;
                    editor.putString(CHOOSE_GIRL_TYPE, CHOOSE_GIRL_GALLERY);
                    editor.putString(IMG_GIRL, uri.toString());
                    mImgGirl.setImageURI(uri);
                    writeToFile(uri.toString(), Utils.FILE_GIRL, this);
                }
                editor.apply();
                startNotifyService();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

            } else if (requestCode == RESULT_CANCELED) {
                Log.d("abc", "cancel");
            }
            if(prefs.getBoolean(LOCK_SCREEN, false)){
                updateLockScreen();
            }
            return;
        }


        if (resultCode == RESULT_FROM_CHANGBG) {
            if (PUT_TYPE.equals(PUT_INTEGER)) {
                editor.putString(PUT_TYPE, PUT_INTEGER);
                String pathAsset = data.getStringExtra(RESULT_FROM_CHANGBG_STRING);
                Bitmap bitmap = bitmapFromAssets(pathAsset);
                mFrameBg.setBackground(new BitmapDrawable(getResources(), scaleBitmap(bitmap, 600, 600)));
                editor.putString(BGDATA, pathAsset);
                writeToFile(pathAsset, Utils.FILE_BG, this);
            } else if (PUT_TYPE.equals(PUT_URI)) {
                editor.putString(PUT_TYPE, PUT_URI);
                uri = Uri.parse(data.getStringExtra(RESULT_FROM_CHANGBG_STRING));
                writeToFile(uri.toString(), Utils.FILE_BG, this);
                if (uri != null) {
                    String uriString = String.valueOf(uri);
                    editor.putString(BGDATA, uriString);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        mFrameBg.setBackground(new BitmapDrawable(getResources(), scaleBitmap(bitmap, 600, 600)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            editor.apply();
            writeToFile(PUT_TYPE, FILE_CONFIG, this);
            if(prefs.getBoolean(LOCK_SCREEN, false)){
                updateLockScreen();
            }
        } else if (resultCode == CHUP_ANH) {
            uri = Uri.parse(data.getStringExtra(CHUP_ANH_STRING));
            CropImage();
            if(prefs.getBoolean(LOCK_SCREEN, false)){
                updateLockScreen();
            }
        }

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

    public void CropImage() {
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 4);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, CROP_REQUEST);
        } catch (ActivityNotFoundException ex) {

        }
    }

    public static Bitmap scaleBitmap(Bitmap b, int maxWidth, int maxHeight) {
        return DecodeIcon.scaleBitmap(b, maxWidth, maxHeight);
    }

    @Override
    protected void onPause() {
        super.onPause();
        putToSave();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_boy:
                CHOOSE_IMAGE = BOY;
                displayAlertDialogForChooseImage();
                break;
            case R.id.img_girl:
                CHOOSE_IMAGE = GIRL;
                displayAlertDialogForChooseImage();
                break;
            case R.id.tv_male:
                displayAlertDialogForMale();
                break;
            case R.id.tv_female:
                displayAlertDialogForFemale();
                break;
            case R.id.wave_view:
                displayAlertDialogForChooseFunction();
                break;
            case R.id.img_diary_main:

                MyAdmobController.showAdsFullBeforeDoAction(new Idelegate() {
                    @Override
                    public void callBack(Object value, int where) {
                        Intent it = new Intent(MainActivity.this, ListDiary.class);
                        startActivity(it);
                    }
                });

                break;
            case R.id.img_setting_main:
                displayAlertDialogForChooseFunction();
                break;
        }
    }

    public static Bitmap getCroppedBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        } else
            return null;
    }

    private void GalleryOpen() {
        GalIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(GalIntent, "Select Image from Gallery"), GALLERY_REQUEST);
    }

    private void displayAlertDialogForFemale() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.setname_for_female, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtFemale = (EditText) dialogView.findViewById(R.id.edt_ten_female);
        Button mBtnHuy = (Button) dialogView.findViewById(R.id.btn_huy_female);
        Button mBtnOK = (Button) dialogView.findViewById(R.id.btn_ok_female);
        mEdtFemale.setText(mTvFemale.getText().toString());

        final AlertDialog alertShowFemale = dialogBuilder.create();
        alertShowFemale.show();

        mBtnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertShowFemale.cancel();
            }
        });
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEdtFemale.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, getString(R.string.type_malename), Toast.LENGTH_SHORT).show();
                } else {
                    mTvFemale.setText(mEdtFemale.getText().toString());
                    startNotifyService();
                    alertShowFemale.cancel();
                }
            }
        });
    }

    private void displayAlertDialogForMale() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.setname_for_male, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtMale = (EditText) dialogView.findViewById(R.id.edt_ten_male);
        Button mBtnHuy = (Button) dialogView.findViewById(R.id.btn_huy_male);
        Button mBtnOK = (Button) dialogView.findViewById(R.id.btn_ok_male);
        mEdtMale.setText(mTvMale.getText().toString());

        final AlertDialog alertShowMale = dialogBuilder.create();
        alertShowMale.show();

        mBtnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertShowMale.cancel();
            }
        });
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEdtMale.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, getString(R.string.type_femalename), Toast.LENGTH_SHORT).show();
                } else {
                    mTvMale.setText(mEdtMale.getText().toString());
                    startNotifyService();
                    alertShowMale.cancel();
                }
            }
        });
    }

    private void displayAlertDialogChooseFont() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.list_font, null);
        dialogBuilder.setView(dialogView);

        ListView mLv = (ListView) dialogView.findViewById(R.id.lv_font);

        mListFont = new ArrayList<>();
        mListFont.add(R.drawable.font_xoxoxa);
        mListFont.add(R.drawable.font_xanadu);
        mListFont.add(R.drawable.font_xcelsion_italic);
        mListFont.add(R.drawable.font_xefus);
        mListFont.add(R.drawable.font_xenowort);
        mListFont.add(R.drawable.font_xeroxsans);
        mListFont.add(R.drawable.font_xeroxserif);
        mListFont.add(R.drawable.font_xeroxserifitalic);
        mListFont.add(R.drawable.font_xped);
        mListFont.add(R.drawable.font_xtrusion);
        mListFont.add(R.drawable.font_xxon);
        mListFont.add(R.drawable.font_yellowjacket);
        mListFont.add(R.drawable.font_yougone);
        mListFont.add(R.drawable.font_allura);
        mListFont.add(R.drawable.font_greatvibes);
        mListFont.add(R.drawable.font_arizonia);
        mListFont.add(R.drawable.font_cac_champagne);
        mListFont.add(R.drawable.font_fff_tusj);
        mListFont.add(R.drawable.font_pacifico);

        ListFontAdapter mAdapter = new ListFontAdapter(this, mListFont);
        mLv.setAdapter(mAdapter);

        final AlertDialog alertShow = dialogBuilder.create();
        alertShow.show();
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    setFont("fonts/Xoxoxa.ttf");
                    alertShow.cancel();
                } else if (i == 1) {
                    setFont("fonts/Xanadu.ttf");
                    alertShow.cancel();
                } else if (i == 2) {
                    setFont("fonts/XcelsionItalic.ttf");
                    alertShow.cancel();
                } else if (i == 3) {
                    setFont("fonts/Xefus.ttf");
                    alertShow.cancel();
                } else if (i == 4) {
                    setFont("fonts/Xenowort.ttf");
                    alertShow.cancel();
                } else if (i == 5) {
                    setFont("fonts/XeroxSansSerifNarrowBoldOblique.ttf");
                    alertShow.cancel();
                } else if (i == 6) {
                    setFont("fonts/XeroxSerifNarrowBoldItalic.ttf");
                    alertShow.cancel();
                } else if (i == 7) {
                    setFont("fonts/XeroxSerifNarrowItalic.ttf");
                    alertShow.cancel();
                } else if (i == 8) {
                    setFont("fonts/XPEDShadow.ttf");
                    alertShow.cancel();
                } else if (i == 9) {
                    setFont("fonts/Xtrusion.ttf");
                    alertShow.cancel();
                } else if (i == 10) {
                    setFont("fonts/XXonXXoff.ttf");
                    alertShow.cancel();
                } else if (i == 11) {
                    setFont("fonts/YellowjacketItalic.ttf");
                    alertShow.cancel();
                } else if (i == 12) {
                    setFont("fonts/You'reGoneItalic.ttf");
                    alertShow.cancel();
                } else if (i == 13) {
                    setFont("fonts/AlluraRegular.ttf");
                    alertShow.cancel();
                } else if (i == 14) {
                    setFont("fonts/GreatVibesRegular.ttf");
                    alertShow.cancel();
                } else if (i == 15) {
                    setFont("fonts/Arizonia-Regular.ttf");
                    alertShow.cancel();
                } else if (i == 16) {
                    setFont("fonts/cac_champagne.ttf");
                    alertShow.cancel();
                } else if (i == 17) {
                    setFont("fonts/FFF_Tusj.ttf");
                    alertShow.cancel();
                } else if (i == 18) {
                    setFont("fonts/Pacifico.ttf");
                    alertShow.cancel();
                }
            }
        });

    }

    private void setFont(String pathFont) {
        tf = Typeface.createFromAsset(getAssets(), pathFont);
        mTvDayInWave.setTypeface(tf);
        mTvTimeStartLove.setTypeface(tf);
        mTvFemale.setTypeface(tf);
        mTvMale.setTypeface(tf);
        mTvTitleBottom.setTypeface(tf);
        mTvLoveForever.setTypeface(tf);
        mTvSecond.setTypeface(tf);
        mTvMinute.setTypeface(tf);
        mTvHour.setTypeface(tf);
        mTvDay.setTypeface(tf);
        mTvMonth.setTypeface(tf);
        mTvYear.setTypeface(tf);
        mTvDayInMain.setTypeface(tf);
        mTvYearStatic.setTypeface(tf);
        mTvMonthStatic.setTypeface(tf);
        mTvDayStatic.setTypeface(tf);
        mTvHourStatic.setTypeface(tf);
        mTvMinuteStatic.setTypeface(tf);
        mTvSecondStatic.setTypeface(tf);

        editor.putString(PATH_FONT, pathFont);
        editor.apply();
    }

    DatabaseHandler mDb;

    private void displayAlertDialogForChooseFunction() {
        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.list_function, null);
        dialogBuilder.setView(dialogView);

        mListFunction = new ArrayList<>();
        mDb = new DatabaseHandler(this);
        final RecyclerView mRcv = (RecyclerView) dialogView.findViewById(R.id.rcv_function);
        final RecycleFunctionAdapter mAdapter = new RecycleFunctionAdapter(mListFunction, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        InputStream is = null;
        try {
            is = getAssets().open("anh_nen/bg_1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        mRcv.setBackground(new BitmapDrawable(bitmap));
        mRcv.setLayoutManager(layoutManager);
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        mListFunction.clear();
        mListFunction.add(new Function(R.drawable.ic_change_background, getString(R.string.change_background)));
        mListFunction.add(new Function(R.drawable.ic_change_day, getString(R.string.change_love_date)));
        mListFunction.add(new Function(R.drawable.ic_title, getString(R.string.change_above_title)));
        mListFunction.add(new Function(R.drawable.ic_title, getString(R.string.change_bottom_title)));
        mListFunction.add(new Function(R.drawable.ic_floating, getString(R.string.set_quick_keys)));
        mListFunction.add(new Function(R.drawable.ic_font, getString(R.string.set_font)));
        mListFunction.add(new Function(R.drawable.ic_color, getString(R.string.change_color_wave)));
        mListFunction.add(new Function(R.drawable.ic_color, getString(R.string.change_bg_color_wave)));
        mListFunction.add(new Function(R.drawable.ic_rate, getString(R.string.rate_us)));
        mListFunction.add(new Function(R.drawable.ic_share, getString(R.string.share)));
        mListFunction.add(new Function(R.drawable.ic_lockscreen, getString(R.string.set_lock_screen)));

        mAdapter.notifyDataSetChanged();

        final AlertDialog alertShowFunction = dialogBuilder.create();
        alertShowFunction.show();
        mRcv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRcv, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        if (i == 0) {
                            MyAdmobController.showAdsFullBeforeDoAction(new Idelegate() {
                                @Override
                                public void callBack(Object value, int where) {
                                    Intent it = new Intent(MainActivity.this, ChangeBackground.class);
                                    startActivityForResult(it, RESULT_FROM_CHANGBG);
                                }
                            });
                            alertShowFunction.cancel();
                        } else if (i == 1) {
                            displayAlertDialogForChangeDay();
                            if(prefs.getBoolean(LOCK_SCREEN, false)){
                                updateLockScreen();
                            }
                            alertShowFunction.cancel();
                        } else if (i == 2) {
                            TITLE = TITLE_ABOVE;
                            displayAlertDialogForChangeTitle();
                            alertShowFunction.cancel();
                        } else if (i == 3) {
                            TITLE = TITLE_BOTTOM;
                            displayAlertDialogForChangeTitle();
                            alertShowFunction.cancel();
                        } else if (i == 4) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(MainActivity.this)) {

                                AlertDialogUtils.showAlertDialog(MainActivity.this, getString(R.string.dialog_capquyen_title), getString(R.string.dialog_capquyen_mess), "Ok", "", false, new Idelegate() {
                                    @Override
                                    public void callBack(Object value, int where) {
                                        //If the draw over permission is not available open the settings screen
                                        //to grant the permission.
                                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                                Uri.parse("package:" + getPackageName()));
                                        startActivity(intent);
                                    }
                                });

                            } else {

                                stopService(itForNotify);

                                final SharedPreferences prefsN = getPreferences(MODE_PRIVATE);
                                Intent itForFloating = new Intent(MainActivity.this, FloatingViewService.class);
                                itForFloating.putExtra(IMG_BOY, prefsN.getString(IMG_BOY, String.valueOf(R.drawable.boy)));
                                itForFloating.putExtra(IMG_GIRL, prefsN.getString(IMG_GIRL, String.valueOf(R.drawable.girl)));
                                itForFloating.putExtra(TV_MALE, mTvMale.getText().toString());
                                itForFloating.putExtra(TV_FEMALE, mTvFemale.getText().toString());
                                itForFloating.putExtra(TIME_START, prefsN.getLong(TIME_START, 0));
                                itForFloating.putExtra(PATH_FONT, prefs.getString(PATH_FONT, Constants.DEFAULT_FONT));
                                editor.putLong(TIME_START, prefsN.getLong(TIME_START, 0));
                                editor.apply();
                                startService(itForFloating);
                                alertShowFunction.cancel();

                            }

                        } else if (i == 5) {
                            displayAlertDialogChooseFont();
                            alertShowFunction.cancel();
                        } else if (i == 6) {
                            showColorPickerDialog();
                            alertShowFunction.cancel();
                        } else if (i == 7) {
                            showColorPickerDialogForBg();
                            alertShowFunction.cancel();
                        } else if (i == 8) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                            } catch (ActivityNotFoundException e) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                            }
                            alertShowFunction.cancel();
                        } else if (i == 9) {
                            Bitmap icon = Utils.takeScreenShortOfRootView(mFrameBg);
                            Utils.shareFacebook(icon, getApplicationContext());
                            alertShowFunction.cancel();
                        } else if (i == 10) {
                            long time = prefs.getLong(TIME_START, 0);
                            writeToFile(String.valueOf(time), Utils.FILE_TIME_START, getApplicationContext());
                            startService(new Intent(MainActivity.this, Sv.class));
                            editor.putBoolean(LOCK_SCREEN, true);
                            editor.apply();
                            alertShowFunction.cancel();
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    private MyReceiver myReceiver;

    private void updateLockScreen(){
        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        stopService(new Intent(MainActivity.this, Sv.class));
        long time = prefs.getLong(TIME_START, 0);
        writeToFile(String.valueOf(time), Utils.FILE_TIME_START, getApplicationContext());
        startService(new Intent(MainActivity.this, Sv.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    public static String getMonthOfYear(int value) {
        String month = "";
        switch (value) {
            case 0:
                month = "JAN";
                break;
            case 1:
                month = "FEB";
                break;
            case 2:
                month = "MAR";
                break;
            case 3:
                month = "APR";
                break;
            case 4:
                month = "MAY";
                break;
            case 5:
                month = "JUN";
                break;
            case 6:
                month = "JUL";
                break;
            case 7:
                month = "AUG";
                break;
            case 8:
                month = "SEP";
                break;
            case 9:
                month = "OCT";
                break;
            case 10:
                month = "NOV";
                break;
            case 11:
                month = "DEC";
                break;
        }
        return month;
    }

    //private boolean mCheckClickCalendar =false;

    private void displayAlertDialogForChangeDay() {
        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        mCurrentSelectedDate = Calendar.getInstance();
        mCurrentSelectedDate.setTimeInMillis(prefs.getLong(TIME_START, 0));
        dialogForCalendar(new MyDateSetListener() {
            @Override
            public void onDateSet(Calendar currentSelectedDate) {
                startAlarm(mCurrentSelectedDate.getTimeInMillis());
                hashMapTime.clear();
                hashMapTime.put(TIME_START, mCurrentSelectedDate.getTimeInMillis());
                editor.putLong(TIME_START, mCurrentSelectedDate.getTimeInMillis());
                editor.putString(DATE_START_LOVE, mTvTimeStartLove.getText().toString());
                editor.apply();
                startNotifyService();
                long hieuTimeMillis = Calendar.getInstance().getTimeInMillis() - mCurrentSelectedDate.getTimeInMillis();
                int numDay = (int) ((hieuTimeMillis + BaseActivityWithDatePickerDialog.HIEU_CHINH_THOI_GIAN) / (60000 * 60 * 24));
                mTvDayInWave.setText(numDay + "");
                if(prefs.getBoolean(LOCK_SCREEN, false)){
                    updateLockScreen();
                }
            }
        });
    }

    private void displayAlertDialogForChangeTitle() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custome_change_title, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtChangeTitle = (EditText) dialogView.findViewById(R.id.edt_title);
        Button mBtnHuy = (Button) dialogView.findViewById(R.id.btn_huy_title);
        Button mBtnOK = (Button) dialogView.findViewById(R.id.btn_ok_title);

        if (TITLE.equals(TITLE_ABOVE)) {
            mEdtChangeTitle.setHint(mTvLoveForever.getText().toString());
        } else if (TITLE.equals(TITLE_BOTTOM)) {
            mEdtChangeTitle.setHint(mTvTitleBottom.getText().toString());
        }

        final AlertDialog alertShowChangeTitle = dialogBuilder.create();
        alertShowChangeTitle.show();

        mBtnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertShowChangeTitle.cancel();
            }
        });
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TITLE.equals(TITLE_ABOVE)) {
                    if (mEdtChangeTitle.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, getString(R.string.change_above_title), Toast.LENGTH_SHORT).show();
                    } else {
                        mTvLoveForever.setText(mEdtChangeTitle.getText().toString());
                        alertShowChangeTitle.cancel();
                    }
                } else if (TITLE.equals(TITLE_BOTTOM)) {
                    if (mEdtChangeTitle.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, getString(R.string.change_bottom_title), Toast.LENGTH_SHORT).show();
                    } else {
                        mTvTitleBottom.setText(mEdtChangeTitle.getText().toString());
                        alertShowChangeTitle.cancel();
                    }
                }

            }
        });
    }

    private void displayAlertDialogForChooseImage() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setActivityTitle("Crop Image")
                .setCropShape(CropImageView.CropShape.OVAL)
                .setCropMenuCropButtonTitle("Done")
                .setRequestedSize(200, 200)
                .setFixAspectRatio(true)
                .setAspectRatio(1, 1)
                .start(MainActivity.this);

    }
}
