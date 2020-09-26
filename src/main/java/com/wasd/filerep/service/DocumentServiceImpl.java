package com.wasd.filerep.service;

import com.wasd.filerep.dao.DocumentDAO;
import com.wasd.filerep.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    DocumentDAO documentDAO;

    @Autowired
    public DocumentServiceImpl(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Override
    public List<Document> findAll() {
        return documentDAO.findAll();
    }

    @Override
    public Document findById(long index) {
        return documentDAO.findById(index);
    }

    @Override
    public void save(Document document) {
        documentDAO.save(document);
    }

    @Override
    public void deleteById(long index) {
        documentDAO.deleteById(index);
    }
}
