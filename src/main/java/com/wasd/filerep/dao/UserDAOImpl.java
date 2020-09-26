package com.wasd.filerep.dao;

import com.wasd.filerep.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(int index) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, index);
        return user;
    }

    @Override
    public void save(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
    }

    @Override
    public void deleteById(int index) {
        Session session = entityManager.unwrap(Session.class);
        Query q = session.createQuery("delete from User where id=:user_id");
        q.setParameter("user_id", index);
        q.executeUpdate();
    }
}
