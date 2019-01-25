package com.SpEx7.DAO;

import com.SpEx7.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean authorization(User user) {
        Session session = sessionFactory.getCurrentSession();
        User checkUser = session.get(User.class, user.getLogin());
        return user.getPassword().equals(checkUser.getPassword());
    }
}
