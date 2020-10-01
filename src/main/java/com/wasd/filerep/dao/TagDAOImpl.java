package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Tag;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {
    EntityManager entityManager;

    @Autowired
    public TagDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Tag> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Tag> q = session.createQuery("from Tag", Tag.class);
        return q.getResultList();
    }

    @Override
    public Tag findById(int index) {
        Session session = entityManager.unwrap(Session.class);
        Tag tag = session.get(Tag.class, index);
        return tag;
    }

    @Override
    public Tag findByTitle(String title) {
        Session session = entityManager.unwrap(Session.class);
        Query<Tag> q = session.createQuery("from Tag where title=:searchedTitle", Tag.class);
        q.setParameter("searchedTitle", title);
        Tag tag = null;
        try{
            tag = q.getSingleResult();
            return tag;
        }catch (NoResultException e){
            return tag;
        }

    }

    @Override
    public void save(Tag tag) {
        Session session = entityManager.unwrap(Session.class);
        Transaction txn = session.beginTransaction();
        if(tag.getId() != 0){
            Tag existingTag = session.get(Tag.class, tag.getId());
            existingTag.setTitle(tag.getTitle());
            session.saveOrUpdate(existingTag);

        }else{
            session.saveOrUpdate(tag);
        }
        txn.commit();
    }

    @Override
    public void deleteById(int index) {
        Session session = entityManager.unwrap(Session.class);
        Query q = session.createQuery("delete from Tag where id=:tagId");
        q.setParameter("tagId", index);
        q.executeUpdate();
    }
}
