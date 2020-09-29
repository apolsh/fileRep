package com.wasd.filerep.service;

import com.wasd.filerep.dao.DocumentDAO;
import com.wasd.filerep.dao.DocumentVersionDAO;
import com.wasd.filerep.entity.DocumentVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentVersionServiceImpl implements DocumentVersionService {

    DocumentVersionDAO documentVersionDAO;
    DocumentDAO documentDAO;

    @Autowired
    public DocumentVersionServiceImpl(DocumentVersionDAO documentVersionDAO, DocumentDAO documentDAO) {
        this.documentVersionDAO = documentVersionDAO;
        this.documentDAO = documentDAO;
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
        documentVersion.getDocument().setActualVersion(documentVersion);
        documentDAO.save(documentVersion.getDocument());
    }

    @Override
    public void deleteById(int index) {
        documentVersionDAO.deleteById(index);
    }
}
