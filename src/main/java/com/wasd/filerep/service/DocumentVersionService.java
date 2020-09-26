package com.wasd.filerep.service;

import com.wasd.filerep.entity.DocumentVersion;
import com.wasd.filerep.entity.Folder;

import java.util.List;

public interface DocumentVersionService {

    public List<DocumentVersion> findAll();

    public DocumentVersion findById(long index);

    public void save(DocumentVersion documentVersion);

    public void deleteById(int index);
}
