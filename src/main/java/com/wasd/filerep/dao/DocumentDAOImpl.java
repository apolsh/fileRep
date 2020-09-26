package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Document;
import com.wasd.filerep.entity.Section;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO {

    private EntityManager entityManager;

    @Autowired
    public DocumentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Document> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Document> query = session.createQuery("from Document", Document.class);
        return query.getResultList();
    }

    @Override
    public Document findById(long index) {
        Session session = entityManager.unwrap(Session.class);
        Document document = session.get(Document.class, index);
        return document;
    }

    @Override
    public void save(Document document) {
        Session session = entityManager.unwrap(Session.class);
        if(document.getId() != 0 && document.getVersions() == null){
            Document existingDocument = session.get(Document.class, document.getId());
            existingDocument.setTitle(document.getTitle());
            existingDocument.setActualVersion(document.getActualVersion());
            session.saveOrUpdate(existingDocument);
        }else{
            session.saveOrUpdate(document);
        }
    }

    @Override
    public void deleteById(long index) {
        Session session = entityManager.unwrap(Session.class);
        Query q = session.createQuery("delete from Document where id=:document_id");
        q.setParameter("document_id", index);
        q.executeUpdate();
    }
}
