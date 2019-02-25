package com.SpEx7.DAO;

import com.SpEx7.entity.News;

import java.util.List;

public interface NewsDAO {
    void addNews(News news);

    void updateNews(News news);

    List<News> listNews();

    News getNewsById(int Id);

    void deleteNews(int Id);
}
