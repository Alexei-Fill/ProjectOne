package com.SpEx7.service;

import com.SpEx7.DAO.NewsDAO;
import com.SpEx7.entity.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsDAO newsDAO;

    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    @Transactional
    public void addNews(News news) {
        newsDAO.addNews(news);
    }

    @Override
    @Transactional
    public void updateNews(News news) {
        newsDAO.updateNews(news);
    }

    @Override
    @Transactional
    public List<News> listNews() {
        return newsDAO.listNews();
    }

    @Override
    @Transactional
    public News getNewsById(int id) {
        return newsDAO.getNewsById(id);
    }

    @Override
    @Transactional
    public void deleteNews(int id) {
        newsDAO.deleteNews(id);
    }
}
