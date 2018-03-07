package com.example.hp6300pro.demngayyeu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hp6300pro.demngayyeu.R;

import java.util.ArrayList;

/**
 * Created by HP 6300 Pro on 12/1/2017.
 */

public class RecycleBackgroundAdapter extends RecyclerView.Adapter<RecycleBackgroundAdapter.ViewHolder> {

    private ArrayList<Integer> mListBg;
    private Context mContext;

    public RecycleBackgroundAdapter(ArrayList<Integer> mListBg, Context mContext) {
        this.mListBg = mListBg;
        this.mContext = mContext;
    }

    @Override
    public RecycleBackgroundAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_background, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleBackgroundAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mImgBg.setImageResource(mListBg.get(i));
    }

    @Override
    public int getItemCount() {
        return mListBg.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgBg;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgBg = (ImageView) itemView.findViewById(R.id.img_bg);
            mContext = itemView.getContext();
        }
    }
}
