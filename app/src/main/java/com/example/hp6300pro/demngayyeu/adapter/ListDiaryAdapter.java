package com.example.hp6300pro.demngayyeu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.activity.MainActivity;
import com.example.hp6300pro.demngayyeu.model.Diary;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP 6300 Pro on 1/4/2018.
 */

public class ListDiaryAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Diary> mListDiary;

    public ListDiaryAdapter(Context mContext, ArrayList<Diary> mListDiary) {
        this.mContext = mContext;
        this.mListDiary = mListDiary;
    }

    @Override
    public int getCount() {
        return mListDiary.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mRow = inflater.inflate(R.layout.item_diary, null);
        ImageView mImgDiary = (ImageView) mRow.findViewById(R.id.img_item_diary);
        TextView mTvDate = (TextView) mRow.findViewById(R.id.tv_date_item_diary);
        TextView mTvTitle = (TextView) mRow.findViewById(R.id.tv_title_item_diary);

        Diary diary = mListDiary.get(i);
        try { //DuyLH - fix code
            mImgDiary.setImageBitmap(MainActivity.scaleBitmap
                    (MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.parse(diary.getUriBgCollapsing())), 600, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mTvDate.setText(diary.getDate());
        mTvTitle.setText(diary.getTitleLove());

        return mRow;
    }

//    public static Bitmap RotateBitmap(Bitmap source, float angle) {
//        Matrix matrix = new Matrix();
//        matrix.postRotate(angle);
//        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
//    }
}
