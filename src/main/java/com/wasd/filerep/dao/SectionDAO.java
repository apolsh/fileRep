package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;

import java.util.List;

public interface SectionDAO {

    public List<Section> findAll();

    public Section findById(int index);

    public void save(Section section);

    public void deleteById(int index);
}
