package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        // Todo remover mock de noticias
        List<News> news = new ArrayList<>();
        news.add(new News("Ferroviária tem desfalque importante", ""));
        news.add(new News("Feroviário joga no Sábado", ""));
        news.add(new News("Copa do Mundo Feminina está terminando", ""));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}