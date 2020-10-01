package com.wasd.filerep.service;

import com.wasd.filerep.entity.Tag;
import com.wasd.filerep.entity.User;

import java.util.List;

public interface TagService {

    public List<Tag> findAll();

    public Tag findById(int index);

    public Tag findByTitle(String title);

    public void save(Tag tag);

    public void deleteById(int index);
}
