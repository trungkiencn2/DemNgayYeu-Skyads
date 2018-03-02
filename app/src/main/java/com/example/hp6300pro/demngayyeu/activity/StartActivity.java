package com.example.hp6300pro.demngayyeu.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.utils.Constants;

import java.util.Calendar;

public class StartActivity extends BaseActivityWithDatePickerDialog implements View.OnClickListener { //

    private TextView mTvDay, mTvCountDay, mTvCountOnLove, mTvPickDay, mTvDayStatic;
    private Button mBtnYeuThoi;
    private CheckBox mCb;

    public static final String START_LOVE = "START_LOVE";
    public static final String CONTINUE_LOVE = "CONTINUE_LOVE";
    public static String START_OR_CONTINUE = START_LOVE;
    //private Calendar thatDay = Calendar.getInstance();
    private SharedPreferences.Editor mEditor;
    private android.os.Handler mHandler = new android.os.Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isHienThiQcBanner = false;

        setContentView(R.layout.activity_start_app);
        initView();
        setFont();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        if (prefs.getString(START_OR_CONTINUE, "").equals(CONTINUE_LOVE)) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void initView() {
        mTvDay = (TextView) findViewById(R.id.tv_day_in_startapp);
        mTvCountDay = (TextView) findViewById(R.id.tv_count_day_in_startapp);
        mTvCountOnLove = (TextView) findViewById(R.id.tv_count_on_love_startapp);
        mTvPickDay = (TextView) findViewById(R.id.tv_pick_start_startapp);
        mTvDayStatic = (TextView) findViewById(R.id.tv_day_static_startapp);
        mCb = (CheckBox) findViewById(R.id.cb);
        mBtnYeuThoi = (Button) findViewById(R.id.btn_yeuthoi_startapp);

        mTvDay.setOnClickListener(this);
        //mCb.setOnClickListener(this);

        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTvCountDay.setText("0");
                    mTvDay.setText(getCurrentDateInCalendarInCalendar());

                    if (mCurrentSelectedDate == null) {
                        mCurrentSelectedDate = Calendar.getInstance();
                    }

                    mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());
                }
            }
        });
        mBtnYeuThoi.setOnClickListener(this);
        mTvDay.setText(getCurrentDateInCalendarInCalendar());
    }

    private void setFont() {
        Typeface tf = Typeface.createFromAsset(getAssets(), Constants.DEFAULT_FONT);
        mTvDay.setTypeface(tf);
        mTvCountDay.setTypeface(tf);
        mTvCountOnLove.setTypeface(tf);
        mTvPickDay.setTypeface(tf);
        mTvDayStatic.setTypeface(tf);
        mCb.setTypeface(tf);
        mBtnYeuThoi.setTypeface(tf);

    }

    public String getCurrentDateInCalendarInCalendar() {
        Calendar cal = Calendar.getInstance();
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR));
    }

    @Override
    public void onClick(View view) {
        mEditor = getPreferences(MODE_PRIVATE).edit();

        switch (view.getId()) {
            case R.id.tv_day_in_startapp:
                dialogForCalendar(new MyDateSetListener() {
                    @Override
                    public void onDateSet(Calendar currentSelectedDate) {

                        int dayOfMonth = currentSelectedDate.get(Calendar.DAY_OF_MONTH);
                        int month = currentSelectedDate.get(Calendar.MONTH) + 1;
                        int year = currentSelectedDate.get(Calendar.YEAR);

                        mTvDay.setText(dayOfMonth + "-" + (month) + "-" + year);

                        long hieuDay = Calendar.getInstance().getTimeInMillis() - mCurrentSelectedDate.getTimeInMillis() + HIEU_CHINH_THOI_GIAN;

                        long countDay = hieuDay / 86400000;

                        mTvCountDay.setText(countDay + ""); //
                    }
                });
                break;
            case R.id.cb:


                break;
            case R.id.btn_yeuthoi_startapp:
                mEditor.putString(START_OR_CONTINUE, CONTINUE_LOVE);
                mEditor.apply();
                Intent it = new Intent(this, MainActivity.class);

                if (mCurrentSelectedDate == null) {
                    mCurrentSelectedDate = Calendar.getInstance();
                }

                mEditor.putLong(MainActivity.TIME_START, mCurrentSelectedDate.getTimeInMillis());
                mEditor.apply();

                it.putExtra(MainActivity.TIME_START, mCurrentSelectedDate.getTimeInMillis());
                startActivity(it);
                break;
        }
    }

    private long getTimeFromCalendar(Calendar c) {
        return c.getTimeInMillis();
    }
}
