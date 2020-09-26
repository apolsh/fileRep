package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Document;
import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SectionDAOImpl implements SectionDAO {

    private EntityManager entityManager;

    @Autowired
    public SectionDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Section> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Section> q = session.createQuery("from Section", Section.class);
        List<Section> sections = q.getResultList();
        return sections;
    }

    @Override
    public Section findById(int index) {
        Session session = entityManager.unwrap(Session.class);
        Section section = session.get(Section.class, index);
        return section;
    }

    @Override
    public void save(Section section) {
        Session session = entityManager.unwrap(Session.class);
        if(section.getId() != 0 && section.getFolders() == null){
            Section existingSection = session.get(Section.class, section.getId());
            existingSection.setTitle(section.getTitle());
            session.saveOrUpdate(existingSection);
        }else{
            session.saveOrUpdate(section);
        }

    }

    @Override
    public void deleteById(int index) {
        Session session = entityManager.unwrap(Session.class);
        Query q = session.createQuery("delete from Section where id=:sectionId");
        q.setParameter("sectionId", index);
        q.executeUpdate();
    }

}
