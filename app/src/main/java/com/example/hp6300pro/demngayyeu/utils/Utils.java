package com.example.hp6300pro.demngayyeu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import java.io.OutputStream;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by HP 6300 Pro on 1/8/2018.
 */

public class Utils {

    public static final String FILE_BOY = "FILE_BOY";
    public static final String FILE_GIRL = "FILE_GIRL";
    public static final String FILE_BG = "FILE_BG";
    public static final String FILE_NAME_BOY = "FILE_NAME_BOY";
    public static final String FILE_NAME_GIRL = "FILE_NAME_GIRL";
    public static final String FILE_TIME_START = "FILE_TIME_START";

    public static void shareFacebook(Bitmap mBitmap, Context context) {

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setFlags(FLAG_ACTIVITY_NEW_TASK);
        share.setType("image/jpeg");

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "title");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);
        OutputStream outstream;
        try {
            outstream = context.getContentResolver().openOutputStream(uri);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
            outstream.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        share.putExtra(Intent.EXTRA_STREAM, uri);
        Intent itShare = Intent.createChooser(share, "Share Image");
        itShare.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(itShare);
    }

    public static Bitmap takeScreenShortOfRootView(View v){
        return takeScreenShort(v.getRootView());
    }

    public static Bitmap takeScreenShort(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }


}
