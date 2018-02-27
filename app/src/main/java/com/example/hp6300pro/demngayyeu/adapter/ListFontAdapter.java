package com.example.hp6300pro.demngayyeu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp6300pro.demngayyeu.R;

import java.util.ArrayList;

/**
 * Created by HP 6300 Pro on 12/26/2017.
 */

public class ListFontAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Integer> mListFont;

    public ListFontAdapter(Context mContext, ArrayList<Integer> mListFont) {
        this.mContext = mContext;
        this.mListFont = mListFont;
    }

    @Override
    public int getCount() {
        return mListFont.size();
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
        View mRow = inflater.inflate(R.layout.item_list_font, null);
        ImageView mImg = (ImageView) mRow.findViewById(R.id.img_font);
        Integer image = mListFont.get(i);
        mImg.setImageResource(image);
        // ban broad cast
        return mRow;
    }
}
