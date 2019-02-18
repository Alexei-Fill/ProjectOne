package com.SpEx7.DAO;

import com.SpEx7.entity.News;

import com.SpEx7.entity.News_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class NewsDAOImpl implements NewsDAO {

    private final String ID_NEWS = "id";
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //Named Query
    @Override
    public void addNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedNativeQuery("@INSERT_INTO_NEWS");
        query.setParameter("brief", news.getBrief());
        query.setParameter("content", news.getContent());
        query.setParameter("date_news", news.getDate());
        query.setParameter("title", news.getTitle());
        query.executeUpdate();
    }

    //Criteria
    @Override
    public void updateNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<News> criteriaQuery = criteriaBuilder.createCriteriaUpdate(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        criteriaQuery.set(News_.title, news.getTitle());
        criteriaQuery.set(News_.brief, news.getBrief());
        criteriaQuery.set(News_.content, news.getContent());
        criteriaQuery.set(News_.date, news.getDate());
        criteriaQuery.where(criteriaBuilder.equal(root.get(News_.id), news.getId()));
        session.createQuery(criteriaQuery).executeUpdate();
    }

    //SQL
    @Override
    public List<News> listNews() {
        Session session = sessionFactory.getCurrentSession();
        final String SQLQuery = "select * from NEWS";
        Query query = session.createNativeQuery(SQLQuery);
        List<News> listNews = new ArrayList<>();
        List<Object[]> list = ((NativeQuery) query).list();
        for (Object[] obj : list) {
            News news = new News();
            news.setId(Integer.parseInt(obj[0].toString()));
            news.setTitle(obj[1].toString());
//            news.setDate(LocalDate.parse(obj[2].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
            news.setDate(LocalDate.parse(obj[2].toString()));
            if (obj[3] != null) {
                news.setBrief(obj[3].toString());
            }
            news.setContent(obj[4].toString());
            listNews.add(news);
        }
        Collections.sort(listNews, Comparator.comparing(News::getDate).thenComparing(News::getTitle));
        return listNews;
    }

    //JPQL
    @Override
    public News getNewsById(int id) {
        Session session = sessionFactory.getCurrentSession();
        final String JPQLQuery = "SELECT n FROM News n WHERE n.id = :id";
        Query query = session.createQuery(JPQLQuery, News.class);
        query.setParameter(ID_NEWS, id);
        return (News) query.getSingleResult();
    }

    //HQL
    @Override
    public void deleteNews(int id) {
        Session session = sessionFactory.getCurrentSession();
        final String checkNews = "from News n where n.id = :id";
        final String deleteNews = "delete from News n where n.id = :id";
        Query checkNewsQuery = session.createQuery(checkNews);
        checkNewsQuery.setParameter(ID_NEWS, id);
        News news = (News) checkNewsQuery.getSingleResult();
        System.out.println(news.toString());
        if (news != null) {
            Query deleteNewsQuery = session.createQuery(deleteNews);
            deleteNewsQuery.setParameter(ID_NEWS, id);
            deleteNewsQuery.executeUpdate();
        }
    }
}
