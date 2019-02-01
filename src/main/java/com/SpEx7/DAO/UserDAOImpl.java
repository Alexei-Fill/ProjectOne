package com.SpEx7.DAO;

import com.SpEx7.entity.PortalUser;
import com.SpEx7.entity.PortalUser_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public PortalUser loadUserByUsername(String login) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PortalUser> criteriaQuery = criteriaBuilder.createQuery(PortalUser.class);
        Root<PortalUser> root = criteriaQuery.from(PortalUser.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(PortalUser_.login), login));
        Query query = session.createQuery(criteriaQuery);
        try {
            PortalUser portalUser = (PortalUser) query.getSingleResult();
            return portalUser;
        } catch (NoResultException nre){
            return null;
        }
    }

    @Override
    @Transactional
    public void addUser(PortalUser portalUser) {
        Session session = sessionFactory.getCurrentSession();
        session.persist( portalUser);
    }
}
