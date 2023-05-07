package com.chunxia.articlereader.activity;

import static com.chunxia.articlereader.activity.IntentConstant.ARTICLE_ACTIVITY_NEWS_KEY;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chunxia.articlereader.R;
import com.chunxia.articlereader.adapter.AdapterListNews;
import com.chunxia.articlereader.model.News;
import com.chunxia.articlereader.model.NewsDatabaseHelper;
import com.chunxia.articlereader.model.ListNewsActivityViewModel;
import com.chunxia.articlereader.model.NewsHelper;
import com.chunxia.articlereader.tools.Tools;


import java.util.List;

public class ListNewsCard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterListNews mAdapter;

    private ListNewsActivityViewModel listNewsActivityViewModel;
    private NewsDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news_card);

        initDatabase();
        initToolbar();
        initComponent();
        initViewModel();
    }

    private void initDatabase() {
        dbHelper = new NewsDatabaseHelper(this);
    }

    private void initViewModel() {
        // 初始化 NewsViewModel
        listNewsActivityViewModel = new ViewModelProvider(this).get(ListNewsActivityViewModel.class);

        // 观察 LiveData
        listNewsActivityViewModel.getNewsListLiveData().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> newsList) {
                mAdapter.setItems(newsList);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                NewsHelper.initNewsDatabaseFromServer(ListNewsCard.this, 20, new Runnable() {
                    @Override
                    public void run() {
                        listNewsActivityViewModel.postNewsList(dbHelper.getAllNews());
                    }
                });
            }
        }).start();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_ATOP);
        toolbar.setTitleTextColor(getResources().getColor(R.color.grey_80));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News Card");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        List<News> items = dbHelper.getAllNews();

        //set data and list adapter
        mAdapter = new AdapterListNews(this, items, R.layout.item_news_card2);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListNews.OnItemClickListener() {
            @Override
            public void onItemClick(View view, News obj, int position) {
                Intent intent = new Intent(ListNewsCard.this, ArticleSimple.class);
                intent.putExtra(ARTICLE_ACTIVITY_NEWS_KEY, obj);
                startActivity(intent);
            }
        });
    }

}
