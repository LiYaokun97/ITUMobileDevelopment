package com.chunxia.articlereader.model;

import static com.chunxia.articlereader.model.DataGenerator.getAllImages;
import static com.chunxia.articlereader.model.DataGenerator.getRandomIndex;

import android.content.Context;

import com.chunxia.articlereader.newsapi.NewsClientApi;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsHelper {

    public static void initNewsDatabaseFromLocal(Context context, int count) {
        NewsDatabaseHelper dbHelper = new NewsDatabaseHelper(context);
        List<News> allNews = dbHelper.getAllNews();
        if (allNews == null || allNews.size() == 0) {
            dbHelper.insertAllNews(DataGenerator.getNewsData(context, count));
        }
    }

    public static void initNewsDatabaseFromServer(Context context, int count, Runnable onSuccess) {
        NewsDatabaseHelper dbHelper = new NewsDatabaseHelper(context);

        NewsClientApi.getNews(new NewsApiClient.ArticlesResponseCallback() {
            @Override
            public void onSuccess(ArticleResponse response) {
                List<Integer> images = getAllImages(context);
                ArrayList<News> arrayList = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    String title = response.getArticles().get(i).getTitle();
                    String subtitle = response.getArticles().get(i).getDescription();
                    Integer image = images.get(getRandomIndex(images.size()));
                    String content = response.getArticles().get(i).getContent();
                    arrayList.add(new News(image, title, subtitle, getCurrentDateTime(), content));
                }
                dbHelper.replaceNewsList(arrayList);
                onSuccess.run();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }


    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

}
