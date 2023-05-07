package com.chunxia.articlereader.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NewsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NEWS = "news";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SUBTITLE = "subtitle";
    private static final String COLUMN_DATE = "date";

    private static final String CONTENT_DATE = "content";
    public NewsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NEWS = "CREATE TABLE " + TABLE_NEWS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_IMAGE + " INTEGER, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_SUBTITLE + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + CONTENT_DATE + " TEXT)";
        db.execSQL(CREATE_TABLE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
        onCreate(db);
    }

    // 插入News数据
    public void insertNews(News news) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, news.getImage());
        values.put(COLUMN_TITLE, news.getTitle());
        values.put(COLUMN_SUBTITLE, news.getSubtitle());
        values.put(COLUMN_DATE, news.getDate());
        values.put(CONTENT_DATE, news.getContent());
        db.insert(TABLE_NEWS, null, values);
        db.close();
    }

    // 读取所有News数据
    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NEWS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int imageIndex = cursor.getColumnIndex(COLUMN_IMAGE);
                int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                int subtitleIndex = cursor.getColumnIndex(COLUMN_SUBTITLE);
                int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
                int contentIndex = cursor.getColumnIndex(CONTENT_DATE);

                if (imageIndex == -1 || titleIndex == -1 || subtitleIndex == -1 || dateIndex == -1 || contentIndex == -1){
                    return null;
                }

                News news = new News(
                        cursor.getInt(imageIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(subtitleIndex),
                        cursor.getString(dateIndex),
                        cursor.getString(contentIndex)
                );
                newsList.add(news);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return newsList;
    }

    // 插入所有News数据
    public void insertAllNews(List<News> newsList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        for (News news : newsList) {
            values = new ContentValues();
            values.put(COLUMN_IMAGE, news.getImage());
            values.put(COLUMN_TITLE, news.getTitle());
            values.put(COLUMN_SUBTITLE, news.getSubtitle());
            values.put(COLUMN_DATE, news.getDate());
            values.put(CONTENT_DATE, news.getContent());
            db.insert(TABLE_NEWS, null, values);
        }
        db.close();
    }

    public void replaceNewsList(List<News> newsList) {
        SQLiteDatabase db = this.getWritableDatabase();

        // 删除所有现有行
        db.delete(TABLE_NEWS, null, null);

        // 插入新的 News 列表
        ContentValues values;
        for (News news : newsList) {
            values = new ContentValues();
            values.put(COLUMN_IMAGE, news.getImage());
            values.put(COLUMN_TITLE, news.getTitle());
            values.put(COLUMN_SUBTITLE, news.getSubtitle());
            values.put(COLUMN_DATE, news.getDate());
            values.put(CONTENT_DATE, news.getContent());
            db.insert(TABLE_NEWS, null, values);
        }

        db.close();
    }


}
