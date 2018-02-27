package com.example.hp6300pro.demngayyeu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.adapter.ListDiaryAdapter;
import com.example.hp6300pro.demngayyeu.ads.MyAdmobController;
import com.example.hp6300pro.demngayyeu.ads.MyBaseActivityWithAds;
import com.example.hp6300pro.demngayyeu.database.DatabaseHandler;
import com.example.hp6300pro.demngayyeu.model.Diary;
import com.example.hp6300pro.demngayyeu.utils.Idelegate;

import java.util.ArrayList;

import static android.R.attr.fragment;

public class ListDiary extends MyBaseActivityWithAds implements View.OnClickListener {

    public static final String CLICK_ITEM_DIARY = "CLICK_ITEM_DIARY";
    public static final String CLICK_ADD_DIARY = "CLICK_ADD_DIARY";
    public static String SET_CLICK = CLICK_ADD_DIARY;

    private FloatingActionButton mFab;
    private ListView mLvDiary;
    private ImageView mImgBgListDiary;
    private DatabaseHandler mDb;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TO_ADD_DIARY) {
            addEvent();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_diary);
        initView();

        addEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initView() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mLvDiary = (ListView) findViewById(R.id.lv_diary);
        mImgBgListDiary = (ImageView) findViewById(R.id.img_bg_listdiary);
        Glide.with(this).load(Uri.parse("file:///android_asset/anh_nen/bg_default.jpg")).into(mImgBgListDiary);
    }

    private ListDiaryAdapter mListDiaryAdapter;
    private ArrayList<Diary> mListDiary;

    private void addEvent() {
        mDb = new DatabaseHandler(this);
        mListDiary = new ArrayList<>();
        mListDiaryAdapter = new ListDiaryAdapter(this, mListDiary);
        mLvDiary.setAdapter(mListDiaryAdapter);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                mListDiary.clear();
                mListDiary.addAll(mDb.getListDiary());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mListDiaryAdapter.notifyDataSetChanged();
            }
        }.execute();

        mLvDiary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                MyAdmobController.showAdsFullBeforeDoAction(new Idelegate() {
                    @Override
                    public void callBack(Object value, int where) {
                        SET_CLICK = CLICK_ITEM_DIARY;
                        Intent it = new Intent(getApplicationContext(), AddDiary.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(MainActivity.PUT_DIARY, mListDiary.get(i));
                        it.putExtras(bundle);
                        startActivityForResult(it, REQUEST_TO_ADD_DIARY);
                    }
                });

            }
        });

        mLvDiary.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ListDiary.this);
                builder.setIcon(R.drawable.ic_alone);
                builder.setMessage(getString(R.string.are_you_sure));
                builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDb.deleteDiary(mListDiary.get(i).getId());
                        mListDiary = mDb.getListDiary();
                        mListDiaryAdapter = new ListDiaryAdapter(ListDiary.this, mListDiary);
                        mLvDiary.setAdapter(mListDiaryAdapter);
                        mListDiaryAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                android.app.AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
        mFab.setOnClickListener(this);
    }

    private final int REQUEST_TO_ADD_DIARY = 102;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:

                MyAdmobController.showAdsFullBeforeDoAction(new Idelegate() {
                    @Override
                    public void callBack(Object value, int where) {
                        SET_CLICK = CLICK_ADD_DIARY;
                        startActivityForResult(new Intent(ListDiary.this, AddDiary.class), REQUEST_TO_ADD_DIARY);
                    }
                });

                break;
        }
    }
}
