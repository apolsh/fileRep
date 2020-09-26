package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
        if(folder.getId() != 0 && folder.getDocuments() == null){
            Section existingFolder = session.get(Section.class, folder.getId());
            existingFolder.setTitle(folder.getTitle());
            session.saveOrUpdate(existingFolder);
        }else{
            session.saveOrUpdate(folder);
        }
    }

    @Override
    public void deleteById(int index) {
        Session session = entityManager.unwrap(Session.class);
        Query q = session.createQuery("delete from Folder where id=:folderId");
        q.setParameter("folderId", index);
        q.executeUpdate();
    }
}
