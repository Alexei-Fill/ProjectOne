package com.SpEx7.service;

import com.SpEx7.DAO.NewsDAO;
import com.SpEx7.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
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
    @Transactional(readOnly = true)
    public List<News> listNews() {
        return newsDAO.listNews();
    }

    @Override
    @Transactional(readOnly = true)
    public News getNewsById(int id) {
        return newsDAO.getNewsById(id);
    }

    @Override
    @Transactional
    public void deleteNews(List<Integer> id) {
        for (Integer i : id) {
            newsDAO.deleteNews(i);
        }
    }
}
