package com.example.hp6300pro.demngayyeu.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.ads.MyBaseActivityWithAds;
import com.example.hp6300pro.demngayyeu.database.DatabaseHandler;
import com.example.hp6300pro.demngayyeu.model.Diary;
import com.example.hp6300pro.demngayyeu.utils.Constants;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.Calendar;

import static com.example.hp6300pro.demngayyeu.activity.MainActivity.getDayOfWeek;
import static com.example.hp6300pro.demngayyeu.activity.MainActivity.getMonthOfYear;
import static com.example.hp6300pro.demngayyeu.activity.MainActivity.getTimeFromCalendar;

public class AddDiary extends MyBaseActivityWithAds implements View.OnClickListener {

    private ImageView mImgClose, mImgGallery, mImgBgCollapsing;
    private EditText mEdtEnterTitleLove, mEdtEnterContentLove;
    private TextView mTvDate;
    private FloatingActionButton mFabSave;
    private Intent GalIntent;
    private Uri mUri;
    private Diary mDiary;
    private DatabaseHandler mDb;

    public static String VUA_CLICK_CAI_GI = "";
    public static final String VUA_CLICK_ADD = "VUA_CLICK_ADD";
    public static final String VUA_CLICK_ITEM = "VUA_CLICK_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        initView();
        AddOrItemDiary();

//        mEdtEnterTitleLove.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (mEditTitle) return false;
//                return true;
//            }
//        });
//        mEdtEnterContentLove.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (mEditContent) return false;
//                return true;
//            }
//        });
    }

    private void initView() {
        mTvDate = (TextView) findViewById(R.id.tv_date_add_diary);
        mImgClose = (ImageView) findViewById(R.id.img_close_add_diary);
        mImgGallery = (ImageView) findViewById(R.id.img_gallery_add_diary);
        mEdtEnterTitleLove = (EditText) findViewById(R.id.edt_enter_title_love_add_diary);
        mEdtEnterContentLove = (EditText) findViewById(R.id.edt_enter_content_love_add_diary);
        mFabSave = (FloatingActionButton) findViewById(R.id.fab_save);
        mImgBgCollapsing = (ImageView) findViewById(R.id.img_bg_collapsing_add_diary);
//        mEdtEnterTitleLove.setImeActionLabel("Done", EditorInfo.IME_ACTION_DONE);
//        mEdtEnterContentLove.setImeActionLabel("Done", EditorInfo.IME_ACTION_DONE);
//        mEdtEnterTitleLove.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_NEXT) {
//                    hideKeyboard();
//
//                    return true;
//                }
//                return false;
//            }
//        });
//        mEdtEnterContentLove.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_NEXT) {
//                    hideKeyboard();
//
//                    return true;
//                }
//                return false;
//            }
//        });
        addEvent();

        mTvDate.setOnClickListener(this);
        mImgClose.setOnClickListener(this);
        mImgGallery.setOnClickListener(this);
        mFabSave.setOnClickListener(this);
        mImgBgCollapsing.setOnClickListener(this);
        mDb = new DatabaseHandler(this);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void AddOrItemDiary() {

        //DuyLH - change code
        if (ListDiary.SET_CLICK.equals(ListDiary.CLICK_ITEM_DIARY)) {
            whenCLickItemDiary();
        }

    }

//    private void whenClickAddDiary() {
//
//    }

    private void whenCLickItemDiary() {
        mDiary = (Diary) getIntent().getExtras().getSerializable(MainActivity.PUT_DIARY);
        mEdtEnterTitleLove.setText(mDiary.getTitleLove());
        mEdtEnterContentLove.setText(mDiary.getContentLove());
        mTvDate.setText(mDiary.getDate());
        //mImgGallery.setImageResource(R.drawable.ic_pen);
        setUnClick();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mDiary.getUriBgCollapsing()));
            mImgBgCollapsing.setImageBitmap(MainActivity.scaleBitmap(bitmap, 600, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private boolean mEditTitle = true, mEditContent = true;

    private void setUnClick() {
//        mEditTitle = false;
//        mEditContent = false;
//        mImgBgCollapsing.setClickable(false);
//        mTvDate.setClickable(false);
//        mFabSave.setVisibility(View.GONE);
    }

    private void setIsClick() {
//        mEditTitle = true;
//        mEditContent = true;
//        mImgBgCollapsing.setClickable(true);
//        mTvDate.setClickable(true);
//        mFabSave.setVisibility(View.VISIBLE);
    }

    private void addEvent() {
        mTvDate.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.YEAR));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date_add_diary:
                alertPickDay();
                break;
            case R.id.img_bg_collapsing_add_diary:
                GalleryOpen();
                break;
            case R.id.img_close_add_diary:
                finish();
                break;
            case R.id.img_gallery_add_diary:
                GalleryOpen();
                break;
            case R.id.fab_save:
                if (ListDiary.SET_CLICK.equals(ListDiary.CLICK_ADD_DIARY)) {
                    mDiary = new Diary(Calendar.getInstance().toString(), mEdtEnterTitleLove.getText().toString(), String.valueOf(mUri), mTvDate.getText().toString(), mEdtEnterContentLove.getText().toString());
                    mDb.addDiary(mDiary);
                    finish();
                } else if (ListDiary.SET_CLICK.equals(ListDiary.CLICK_ITEM_DIARY)) {
                    mDb.updateDiary(mDiary.getId(), new Diary(mDiary.getId(), mEdtEnterTitleLove.getText().toString(), mDiary.getUriBgCollapsing(), mTvDate.getText().toString(), mEdtEnterContentLove.getText().toString()));
                    finish();
                }
        }
    }

    private void GalleryOpen() {
        //DuyLH - crop image
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setActivityTitle("Crop Image")
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .setCropMenuCropButtonTitle("Done")
                .setRequestedSize(Constants.CROP_IMAGE_REQUEST_SIZE, Constants.CROP_IMAGE_REQUEST_SIZE)
                .setFixAspectRatio(true)
                .setAspectRatio(Constants.CROP_RATIO_X, Constants.CROP_RATIO_Y)
                .start(AddDiary.this);
    }

    private void alertPickDay() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.function_change_day, null);
        dialogBuilder.setView(dialogView);

        final TextView mTvThu = (TextView) dialogView.findViewById(R.id.tv_thu_customcalendar);
        final TextView mTvDay = (TextView) dialogView.findViewById(R.id.tv_day_customcalendar);
        final TextView mTvMonth = (TextView) dialogView.findViewById(R.id.tv_month_customcalendar);
        final TextView mTvYear = (TextView) dialogView.findViewById(R.id.tv_year_customcalendar);
        CalendarView mCalendarView = (CalendarView) dialogView.findViewById(R.id.calendar_in_function_change_day);
        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_customcalendar);
        Button mBtnOK = (Button) dialogView.findViewById(R.id.btn_ok_customcalendar);

        Calendar mCa = Calendar.getInstance();
        Calendar mCaMin = Calendar.getInstance();
        mCaMin.set(2017, 0, 1);

        mTvThu.setText(getDayOfWeek(mCa.get(Calendar.DAY_OF_WEEK)));
        mTvDay.setText(mCa.get(Calendar.DAY_OF_MONTH) + "");
        mTvMonth.setText(getMonthOfYear(mCa.get(Calendar.MONTH)));
        mTvYear.setText(mCa.get(Calendar.YEAR) + "");

        mCalendarView.setVerticalScrollBarEnabled(true);
        mCalendarView.setDate(mCa.get(Calendar.DAY_OF_YEAR));
        mCalendarView.setMinDate(getTimeFromCalendar(mCaMin));
        mCalendarView.setMaxDate(getTimeFromCalendar(Calendar.getInstance()));

        final AlertDialog alert = dialogBuilder.create();
        alert.show();

        final Calendar thisDay = Calendar.getInstance();

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                thisDay.set(year, month, dayOfMonth, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND));
                mTvThu.setText(getDayOfWeek(thisDay.get(Calendar.DAY_OF_WEEK)) + "");
                mTvDay.setText(thisDay.get(Calendar.DAY_OF_MONTH) + "");
                mTvMonth.setText(getMonthOfYear(thisDay.get(Calendar.MONTH)));
                mTvYear.setText(thisDay.get(Calendar.YEAR) + "");
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.cancel();
            }
        });

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvDate.setText(thisDay.get(Calendar.DAY_OF_MONTH) + "-" + thisDay.get(Calendar.MONTH) + "-" + thisDay.get(Calendar.YEAR));
                alert.cancel();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            //DuyLH - crop image result
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    mUri = result.getUri();
                    mImgBgCollapsing.setImageURI(mUri);
                    if (mDiary != null) {
                        mDiary.setUriBgCollapsing(mUri.toString());
                    }
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                } else if (requestCode == RESULT_CANCELED) {
                    Log.d("abc", "cancel");
                }
            }
        }
    }
}