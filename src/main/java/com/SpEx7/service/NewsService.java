package com.SpEx7.service;

import com.SpEx7.entity.News;

import java.util.List;

public interface NewsService {
    void addNews (News news);
    void updateNews (News news);
    List<News> listNews();
    News getNewsById(int Id);
    void deleteNews(List<Integer> id);
}
