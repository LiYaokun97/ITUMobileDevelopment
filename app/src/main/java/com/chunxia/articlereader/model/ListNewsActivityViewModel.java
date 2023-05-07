package com.chunxia.articlereader.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ListNewsActivityViewModel extends ViewModel {
    private MutableLiveData<List<News>> newsListLiveData;

    public ListNewsActivityViewModel() {
        newsListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<News>> getNewsListLiveData() {
        return newsListLiveData;
    }

    public void setNewsList(List<News> newsList) {
        newsListLiveData.setValue(newsList);
    }

}