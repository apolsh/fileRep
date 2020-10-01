package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Section;
import com.wasd.filerep.entity.Tag;

import java.util.List;

public interface TagDAO {

    public List<Tag> findAll();

    public Tag findById(int index);

    public Tag findByTitle(String title);

    public void save(Tag tag);

    public void deleteById(int index);
}
