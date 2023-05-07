package com.chunxia.articlereader.activity;

import static com.chunxia.articlereader.activity.IntentConstant.ARTICLE_ACTIVITY_NEWS_KEY;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.chunxia.articlereader.R;
import com.chunxia.articlereader.model.ArticleActivityViewModel;
import com.chunxia.articlereader.model.News;
import com.chunxia.articlereader.tools.Tools;



public class ArticleSimple extends AppCompatActivity {

    private ArticleActivityViewModel articleActivityViewModel;

    private TextView titleView;
    private TextView subtitleView;
    private TextView contentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_simple);
        initToolbar();
        initView();
        initData();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_10);
        Tools.setSystemBarLight(this);
    }


    private void initView() {
        titleView = findViewById(R.id.article_view_title);
        subtitleView = findViewById(R.id.article_view_subtitle);
        contentView = findViewById(R.id.article_view_content);
    }


    private void initData() {
        articleActivityViewModel = new ViewModelProvider(this).get(ArticleActivityViewModel.class);
        News news = getIntent().getParcelableExtra(ARTICLE_ACTIVITY_NEWS_KEY);
        // 观察 LiveData
        articleActivityViewModel.getNewsLiveData().observe(this, new Observer<News>() {
            @Override
            public void onChanged(News news) {
                titleView.setText(news.getTitle());
                subtitleView.setText(news.getSubtitle());
                contentView.setText(news.getContent());
            }
        });

        articleActivityViewModel.setNews(news);
    }

}
