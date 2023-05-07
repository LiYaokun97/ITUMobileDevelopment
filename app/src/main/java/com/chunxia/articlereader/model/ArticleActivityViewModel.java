package com.chunxia.articlereader.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArticleActivityViewModel extends ViewModel {
    private MutableLiveData<News> newsLiveData;

    public ArticleActivityViewModel() {
        newsLiveData = new MutableLiveData<>();
    }

    public LiveData<News> getNewsLiveData() {
        return newsLiveData;
    }

    public void setNews(News news) {
        newsLiveData.setValue(news);
    }

}