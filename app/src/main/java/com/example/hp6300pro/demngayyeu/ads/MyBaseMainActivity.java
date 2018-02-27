package com.example.hp6300pro.demngayyeu.ads;

import android.os.Bundle;

import com.example.hp6300pro.demngayyeu.activity.BaseActivityWithDatePickerDialog;
import com.google.android.gms.ads.MobileAds;


/**
 * Created by a1 on 11/30/17.
 */

public abstract class MyBaseMainActivity extends BaseActivityWithDatePickerDialog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this, MyAdmobController.getApplicationAdsId(getApplicationContext()));

        MyAdmobController.setTypeQuangCao(getApplicationContext());

        MyAdmobController.XulyQCFull(this);

        MyAdmobController.listenNetworkChangeToRequestAdsFull(this);
    }
}
