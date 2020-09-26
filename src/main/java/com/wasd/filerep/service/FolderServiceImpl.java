package com.wasd.filerep.service;

import com.wasd.filerep.dao.FolderDAO;
import com.wasd.filerep.entity.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {

    FolderDAO folderDAO;

    @Autowired
    public FolderServiceImpl(FolderDAO folderDAO) {
        this.folderDAO = folderDAO;
    }

    @Override
    public List<Folder> findAll() {
        return folderDAO.findAll();
    }

    @Override
    public Folder findById(int index) {
        return folderDAO.findById(index);
    }

    @Override
    public void save(Folder folder) {
        folderDAO.save(folder);
    }

    @Override
    public void deleteById(int index) {
        folderDAO.deleteById(index);
    }
}
