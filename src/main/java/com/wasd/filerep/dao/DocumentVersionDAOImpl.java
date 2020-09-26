package com.wasd.filerep.dao;

import com.wasd.filerep.entity.DocumentVersion;
import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DocumentVersionDAOImpl implements DocumentVersionDAO {

    private EntityManager entityManager;

    @Autowired
    public DocumentVersionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DocumentVersion> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<DocumentVersion> q = session.createQuery("from DocumentVersion ", DocumentVersion.class);
        return q.getResultList();
    }

    @Override
    public DocumentVersion findById(long index) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(DocumentVersion.class, index);
    }

    @Override
    public void save(DocumentVersion documentVersion) {
        Session session = entityManager.unwrap(Session.class);
//        Query<DocumentVersion> q = session.createQuery("from DocumentVersion where id=:index", DocumentVersion.class);
//        q.setParameter("index", "a1");
//
//        DocumentVersion dv = q.getSingleResult();
//        dv.setNote("Vasiliy petrovich");
//        System.out.println("===================saving edited====================");
        documentVersion.setId(0);

        session.saveOrUpdate(documentVersion);
    }

    @Override
    public void deleteById(int index) {
        Session session = entityManager.unwrap(Session.class);
    }
}
