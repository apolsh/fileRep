package com.wasd.filerep.service;

import com.wasd.filerep.entity.Section;

import java.util.List;

public interface SectionService {

    public List<Section> findAll();

    public Section findById(int index);

    public void save(Section section);

    public void deleteById(int index);
}
