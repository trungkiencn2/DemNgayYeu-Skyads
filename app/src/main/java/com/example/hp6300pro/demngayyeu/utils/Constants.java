package com.example.hp6300pro.demngayyeu.utils;

import android.os.Environment;

import com.example.hp6300pro.demngayyeu.R;

import java.util.Locale;


/**
 * Created by Admin on 12/21/2017.
 */

public class Constants {
    //public static final String PATH_IMG_EDITOR = Environment.getExternalStorageDirectory().toString() + "/ImageEditor";

    public static final String ROOT_IMAGE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

    //public static final String FOLDER_NAME = "VietChuLenAnh_StatusHay";
    public static final String FOLDER_NAME = "TextOnPhoto_StatusMaker";

    public static final String DEFAULT_FONT = "fonts/FiolexGirlVH.ttf";

    public static final String PATH_IMG_EDITOR = ROOT_IMAGE_PATH + "/" + FOLDER_NAME;


    public static final String locale = Locale.getDefault().toString();


    public static final int CROP_RATIO_X = 1280;
    public static final int CROP_RATIO_Y = 720;

    public static final int CROP_RATIO_X_PORTRAIT = 720;
    public static final int CROP_RATIO_Y_PORTRAIT = 1280;

    public static final int CROP_IMAGE_REQUEST_SIZE = 700;




}
