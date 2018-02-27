package com.example.hp6300pro.demngayyeu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.activity.MainActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HP 6300 Pro on 12/26/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mImages;

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        try {
            mImages = mContext.getAssets().list("anh_nen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.custom_layout_pager, null);
        ImageView mImg = (ImageView) view.findViewById(R.id.img_item_pager);
        for(int i = 0; i<mImages.length; i++){
            Glide.with(mContext).load(Uri.parse("file:///android_asset/anh_nen/"+mImages[position])).into(mImg);
        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    public Bitmap bitmapFromAssets(String path){
        InputStream is = null;
        try {
            is = mContext.getAssets().open("anh_nen/" + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap  bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public String getImageSelected(int i) {
        return "anh_nen/" + mImages[i];
    }
}
