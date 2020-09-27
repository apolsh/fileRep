package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FolderDAOImpl implements FolderDAO {

    private EntityManager entityManager;

    @Autowired
    public FolderDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Folder> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Folder> q = session.createQuery("from Folder", Folder.class);
        return q.getResultList();
    }

    @Override
    public Folder findById(int index) {
        Session session = entityManager.unwrap(Session.class);
        Folder folder = session.get(Folder.class, index);
        return folder;
    }

    @Override
    public void save(Folder folder) {
        Session session = entityManager.unwrap(Session.class);
        Transaction txn = session.beginTransaction();
        session.saveOrUpdate(folder);
        txn.commit();
    }

    public void update(Folder folder){

        Session session = entityManager.unwrap(Session.class);
        Transaction txn = session.beginTransaction();
        session.update(folder);

//        Query q = session.createQuery("update Folder set title = :title" +
//                ", sectionId = :sectionId, folderId = :folderId, note = :note"+
//                " where id = :update_id");
//        q.setParameter("title", folder.getTitle());
//        q.setParameter("sectionId", folder.getSectionId());
//        q.setParameter("folderId", folder.getFolderId());
//        q.setParameter("note", folder.getNote());
//        q.setParameter("update_id", folder.getId());
//        int result = q.executeUpdate();
        txn.commit();
        //System.out.println("================result:" + result);
    }

    @Override
    public void deleteById(int index) {
        Session session = entityManager.unwrap(Session.class);
        Query q = session.createQuery("delete from Folder where id=:folderId");
        q.setParameter("folderId", index);
        q.executeUpdate();
    }
}
