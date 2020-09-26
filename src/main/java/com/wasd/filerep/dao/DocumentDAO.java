package com.wasd.filerep.dao;

import com.wasd.filerep.entity.Document;
import com.wasd.filerep.entity.Section;

import java.util.List;

public interface DocumentDAO {

    public List<Document> findAll();

    public Document findById(long index);

    public void save(Document document);

    public void deleteById(long index);
}
