package com.chunxia.articlereader.newsapi;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

public class NewsClientApi {

    public static void getNews(NewsApiClient.ArticlesResponseCallback callback) {

        NewsApiClient newsApiClient = new NewsApiClient("acc7c892ad85437caa706298eea67c56");
        // /v2/everything
        newsApiClient.getEverything(new EverythingRequest.Builder().q("China").build(), callback);
    }
}
