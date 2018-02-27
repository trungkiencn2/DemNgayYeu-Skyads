package com.example.hp6300pro.demngayyeu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp6300pro.demngayyeu.model.Diary;
import com.example.hp6300pro.demngayyeu.model.Function;

import java.util.ArrayList;

import static android.media.MediaFormat.KEY_DURATION;
import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by HP 6300 Pro on 1/4/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private ArrayList<Diary> mListDiary;
    private ArrayList<Function> mListFunction;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DemNgayYeu";
    private static final String TABLE_DIARY = "DIARY";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE_LOVE = "titleLove";
    private static final String KEY_URI = "uri";
    private static final String KEY_DATE = "date";
    private static final String KEY_CONTENT_LOVE = "contentLove";

    private static final String TABLE_FUNCTION = "FUNCTION";
    private static final String KEY_IMAGE_FUNCTION = "image_function";
    private static final String KEY_TITLE_FUNCTION = "title_function";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MUSIC_TABLE = "CREATE TABLE " + TABLE_DIARY + "("
                + KEY_ID + " TEXT,"
                + KEY_TITLE_LOVE + " TEXT,"
                + KEY_URI + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_CONTENT_LOVE + " TEXT" + ")";

        String CREATE_MUSIC_FUNCTION = "CREATE TABLE " + TABLE_FUNCTION + "("
                + KEY_IMAGE_FUNCTION + " INTEGER,"
                + KEY_TITLE_FUNCTION + " TEXT" + ")";

        db.execSQL(CREATE_MUSIC_TABLE);
        db.execSQL(CREATE_MUSIC_FUNCTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public void addDiary(Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, diary.getId());
        values.put(KEY_TITLE_LOVE, diary.getTitleLove());
        values.put(KEY_URI, diary.getUriBgCollapsing());
        values.put(KEY_DATE, diary.getDate());
        values.put(KEY_CONTENT_LOVE, diary.getContentLove());
        db.insert(TABLE_DIARY, null, values);
        db.close();
    }

    public void addFunction(Function function) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(!checkAddFunction(function)){
            values.put(KEY_IMAGE_FUNCTION, function.getImage());
            values.put(KEY_TITLE_FUNCTION, function.getTitle());
            db.insert(TABLE_FUNCTION, null, values);
        }
//            db.close();


    }

    public boolean checkAddFunction(Function function) {
        mListFunction = getListFunction();
        for (Function f : mListFunction) {
            if (function.getTitle().toString().equals(f.getTitle().toString())) {
                return true;
            }
        }
        return false;
    }

    public void updateDiary(String id, Diary newDiary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String sql = "update " + TABLE_DIARY + " set " + KEY_TITLE_LOVE + " ='" + newDiary.getTitleLove() + "', "
                + KEY_URI + " ='" + newDiary.getUriBgCollapsing() + "', "
                + KEY_DATE + " ='" + newDiary.getDate() + "', "
                + KEY_CONTENT_LOVE + " ='" + newDiary.getContentLove() + "' where " + KEY_ID + " ='" + id + "'";
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<Diary> getListDiary() {
        mListDiary = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + KEY_ID + "," + KEY_TITLE_LOVE + "," + KEY_URI + ","
                + KEY_DATE + "," + KEY_CONTENT_LOVE + " from " + TABLE_DIARY;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            mListDiary.add(new Diary(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4)));
        }
        db.close();
        cursor.close();
        return mListDiary;
    }

    public ArrayList<Function> getListFunction() {
        mListFunction = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + KEY_IMAGE_FUNCTION + "," + KEY_TITLE_FUNCTION + " from " + TABLE_FUNCTION;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            mListFunction.add(new Function(cursor.getInt(0), cursor.getString(1)));
        }
//        db.close();
//        cursor.close();
        return mListFunction;
    }

    public void deleteDiary(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_DIARY + " where " + KEY_ID + " ='" + id + "'";
        db.execSQL(sql);
        db.close();
    }
}
