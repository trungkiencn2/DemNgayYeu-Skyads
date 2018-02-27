package com.example.hp6300pro.demngayyeu.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.ads.MyBaseActivityWithAds;

import java.util.Calendar;

/**
 * Created by a1 on 1/16/18.
 */

public class BaseActivityWithDatePickerDialog extends MyBaseActivityWithAds {

    public static final int HIEU_CHINH_THOI_GIAN = 60000 * 60;


    protected Calendar mCurrentSelectedDate;


    protected void dialogForCalendar(final MyDateSetListener listener) {

        if (mCurrentSelectedDate == null) {
            mCurrentSelectedDate = Calendar.getInstance();
            mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());
        }

        DatePickerDialog mDatePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int selectedmonth, int dayOfMonth) {
                        int month = selectedmonth + 1;

                        mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis()); //refresh to now

                        mCurrentSelectedDate.set(Calendar.YEAR, year);
                        mCurrentSelectedDate.set(Calendar.MONTH, selectedmonth);
                        mCurrentSelectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        //int monthDebug = Calendar.getInstance().get(Calendar.MONTH);

                        // bat truong hop set qua ngay hien tai

                        if (mCurrentSelectedDate.getTimeInMillis() > (System.currentTimeMillis() + HIEU_CHINH_THOI_GIAN)) {

                            Toast.makeText(getApplicationContext(), getString(R.string.mess_validate_quangay), Toast.LENGTH_LONG).show();

                            //refresh to now
                            mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());

                            return;
                        }
                        //



                        listener.onDateSet(mCurrentSelectedDate);


                    }
                }, mCurrentSelectedDate.get(Calendar.YEAR), mCurrentSelectedDate.get(Calendar.MONTH), mCurrentSelectedDate.get(Calendar.DAY_OF_MONTH));
        mDatePicker.setTitle(getString(R.string.select_date_title));
        mDatePicker.show();
    }
}
