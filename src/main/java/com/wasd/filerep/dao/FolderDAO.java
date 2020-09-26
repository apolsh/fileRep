package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;

import java.util.List;

public interface FolderDAO {

    public List<Folder> findAll();

    public Folder findById(int index);

    public void save(Folder folder);

    public void deleteById(int index);

}
