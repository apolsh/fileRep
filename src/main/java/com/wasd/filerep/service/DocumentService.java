package com.wasd.filerep.service;

import com.wasd.filerep.entity.Document;

import java.util.List;

public interface DocumentService {

    public List<Document> findAll();

    public Document findById(long index);

    public void save(Document document);

    public void deleteById(long index);
}
