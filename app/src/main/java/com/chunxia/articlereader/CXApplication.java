package com.chunxia.articlereader;

import android.app.Application;


import com.chunxia.articlereader.model.NewsHelper;


public class CXApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NewsHelper.initNewsDatabaseFromServer(this, 20);
    }


}
