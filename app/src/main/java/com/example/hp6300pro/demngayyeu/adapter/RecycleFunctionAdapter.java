package com.example.hp6300pro.demngayyeu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.model.Function;

import java.util.ArrayList;

/**
 * Created by HP 6300 Pro on 11/30/2017.
 */

public class RecycleFunctionAdapter extends RecyclerView.Adapter<RecycleFunctionAdapter.ViewHolder>{

    private ArrayList<Function> mListFunction;
    private Context mContext;

    public RecycleFunctionAdapter(ArrayList<Function> mListFunction, Context mContext) {
        this.mListFunction = mListFunction;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_function, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mImgFunction.setImageResource(mListFunction.get(i).getImage());
        viewHolder.mTvTitleFunction.setText(mListFunction.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mListFunction.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgFunction;
        TextView mTvTitleFunction;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgFunction = (ImageView) itemView.findViewById(R.id.img_function);
            mTvTitleFunction = (TextView) itemView.findViewById(R.id.tv_title_function);
            mContext = itemView.getContext();
        }
    }
}
