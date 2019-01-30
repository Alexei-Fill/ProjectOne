package com.SpEx7.DAO;

import com.SpEx7.entity.PortalUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PortalUser loadUserByUsername(String login) {
        Session session = sessionFactory.getCurrentSession();
        PortalUser portalUser = session.get(PortalUser.class, login);
        return portalUser;
    }
}
