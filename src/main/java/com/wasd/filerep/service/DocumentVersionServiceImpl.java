package com.wasd.filerep.service;

import com.wasd.filerep.dao.DocumentVersionDAO;
import com.wasd.filerep.entity.DocumentVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentVersionServiceImpl implements DocumentVersionService {

    DocumentVersionDAO documentVersionDAO;

    @Autowired
    public DocumentVersionServiceImpl(DocumentVersionDAO documentVersionDAO) {
        this.documentVersionDAO = documentVersionDAO;
    }

    @Override
    public List<DocumentVersion> findAll() {
        return documentVersionDAO.findAll();
    }

    @Override
    public DocumentVersion findById(long index) {
        return documentVersionDAO.findById(index);
    }

    @Override
    public void save(DocumentVersion documentVersion) {
        documentVersionDAO.save(documentVersion);
    }

    @Override
    public void deleteById(int index) {
        documentVersionDAO.deleteById(index);
    }
}
