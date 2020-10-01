package com.wasd.filerep.service;

import com.wasd.filerep.dao.TagDAO;
import com.wasd.filerep.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    TagDAO tagDAO;

    @Autowired
    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public List<Tag> findAll() {
        return tagDAO.findAll();
    }

    @Override
    public Tag findById(int index) {
        return tagDAO.findById(index);
    }

    @Override
    public Tag findByTitle(String title) {
        return tagDAO.findByTitle(title);
    }

    @Override
    public void save(Tag tag) {
        tagDAO.save(tag);
    }

    @Override
    public void deleteById(int index) {
        tagDAO.deleteById(index);
    }
}
