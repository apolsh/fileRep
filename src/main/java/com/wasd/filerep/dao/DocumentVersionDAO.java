package com.wasd.filerep.dao;

import com.wasd.filerep.entity.DocumentVersion;
import com.wasd.filerep.entity.Section;

import java.util.List;

public interface DocumentVersionDAO {

    public List<DocumentVersion> findAll();

    public DocumentVersion findById(long index);

    public void save(DocumentVersion documentVersion);

    public void deleteById(int index);
}
