package com.chunxia.articlereader;

import android.app.Application;

import com.chunxia.articlereader.model.DataGenerator;
import com.chunxia.articlereader.model.News;
import com.chunxia.articlereader.model.NewsDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CXApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initNewsDatabase();
    }


    private void initNewsDatabase() {
        NewsDatabaseHelper dbHelper = new NewsDatabaseHelper(this);
        List<News> allNews = dbHelper.getAllNews();
        if (allNews == null || allNews.size() == 0) {
            dbHelper.insertAllNews(DataGenerator.getNewsData(this, 10));
        }
    }

}
