package com.wasd.filerep.service;

import com.wasd.filerep.entity.Folder;

import java.util.List;

public interface FolderService {
    public List<Folder> findAll();

    public Folder findById(int index);

    public void save(Folder section);

    public void update(Folder folder);

    public void deleteById(int index);
}
