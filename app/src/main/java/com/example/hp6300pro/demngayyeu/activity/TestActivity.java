package com.example.hp6300pro.demngayyeu.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;

import com.example.hp6300pro.demngayyeu.R;

/**
 * Created by a1 on 1/16/18.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.function_change_day);


        DatePickerDialog mDatePicker = new DatePickerDialog(TestActivity.this,
                R.style.MyDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        int month = selectedmonth + 1;


                        //tvDisplayCheckOutDate.setText(selectedday + "-" + month + "-" + selectedyear);
                    }
                }, 2018, 1, 16);
        mDatePicker.setTitle("Check out date");
        mDatePicker.show();

    }
}
