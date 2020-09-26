package com.wasd.filerep.service;

import com.wasd.filerep.dao.SectionDAO;
import com.wasd.filerep.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    SectionDAO sectionDAO;

    @Autowired
    public SectionServiceImpl(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @Override
    @Transactional
    public List<Section> findAll() {
        return sectionDAO.findAll();
    }

    @Override
    @Transactional
    public Section findById(int index) {
        return sectionDAO.findById(index);
    }

    @Override
    @Transactional
    public void save(Section section) {
         sectionDAO.save(section);
    }

    @Override
    @Transactional
    public void deleteById(int index) {
        sectionDAO.deleteById(index);
    }

}
