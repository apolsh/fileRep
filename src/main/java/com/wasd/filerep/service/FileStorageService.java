package com.wasd.filerep.service;

import com.wasd.filerep.entity.DocumentVersion;
import com.wasd.filerep.storage.ContentStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileStorageService {

    ContentStorage contentStorage;
    DocumentVersionService documentVersionService;

    @Autowired
    public FileStorageService(ContentStorage contentStorage,DocumentVersionService documentVersionService) {
        this.contentStorage = contentStorage;
        this.documentVersionService = documentVersionService;
    }

    public String uploadFile(DocumentVersion versionTemplate, InputStream fileStream){

        String documentId = UUID.randomUUID().toString();
        contentStorage.saveFile("test", documentId, fileStream, versionTemplate.getSize());
        versionTemplate.setStorageId(documentId);
        documentVersionService.save(versionTemplate);
        return String.valueOf(versionTemplate.getId());
    }

    public InputStream downloadFile(String id){
        return contentStorage.downloadFile("test", id);
    }
}
