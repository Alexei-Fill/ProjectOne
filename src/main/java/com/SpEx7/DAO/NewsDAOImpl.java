package com.SpEx7.DAO;

import com.SpEx7.entity.News;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(news);
    }

    @Override
    public void updateNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<News> criteriaQuery = criteriaBuilder.createCriteriaUpdate(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        criteriaQuery.set("news", news).where(criteriaBuilder.equal(root.get("id"), news.getId()));
        Query query = session.createQuery(criteriaQuery);
        List<News> listNews = query.getResultList();
    }

    @Override
    public List<News> listNews() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        List<News> listNews = query.getResultList();
        return  listNews;
    }

    @Override
    public News getNewsById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(News.class, id);
    }

    @Override
    public void deleteNews(int id) {
        Session session = sessionFactory.getCurrentSession();
        News news = session.get(News.class, id);
        if (news !=null){
            session.delete(news);
        }
    }
}
