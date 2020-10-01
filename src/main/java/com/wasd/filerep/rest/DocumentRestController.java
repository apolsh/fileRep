package com.wasd.filerep.rest;

import com.wasd.filerep.entity.Document;
import com.wasd.filerep.entity.DocumentVersion;
import com.wasd.filerep.entity.Tag;
import com.wasd.filerep.entity.User;
import com.wasd.filerep.service.*;
import com.wasd.filerep.wrappers.rest.TreeItemWrapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentRestController {

    private DocumentService documentService;
    private DocumentVersionService documentVersionService;
    private FileStorageService fileStorageService;
    private TagService tagService;

    @Autowired
    UserService userService;

    @Autowired
    public DocumentRestController(DocumentService documentService,
                                  DocumentVersionService documentVersionService,
                                  FileStorageService fileStorageService,
                                  TagService tagService) {
        this.documentService = documentService;
        this.documentVersionService = documentVersionService;
        this.fileStorageService =fileStorageService;
        this.tagService = tagService;
    }

    @GetMapping("/documents")
    public List<Document> findAll(){
        return documentService.findAll();
    }

    @GetMapping("/documents/{document_id}")
    public Document getDocument(@PathVariable int document_id){
        Document document = documentService.findById(document_id);
        if(document == null){
            throw new RuntimeException("document not found " + document_id);
        }
        return document;
    }

    @PostMapping("/documents")
    public Document addDocument(@RequestBody Document document){
        document.setId(0);
        documentService.save(document);
        return document;
    }

    @PutMapping("/documents")
    public Document updateDocument(@RequestBody Document document){
        List<Tag> tagList = document.getTags();
        for(Tag tag : tagList){
            if(tag.getId() == 0){
                Tag existingTag = tagService.findByTitle(tag.getTitle());
                if(existingTag==null){
                    tagService.save(tag);
                }else{
                    tag.setId(existingTag.getId());
                }

            }
        }
        documentService.save(document);
        return document;
    }

    @DeleteMapping("/documents/{document_id}")
    public String deleteDocument(@PathVariable int document_id){
        Document document = documentService.findById(document_id);
        if(document == null){
            throw new RuntimeException("document not found " + document_id);
        }
        documentService.deleteById(document_id);
        return "success";
    }


    //======================VERSIONS=================================================


    @GetMapping("/documents/{document_id}/versions")
    public List<DocumentVersion> getDocumentVersions(@PathVariable long document_id){
        Document document = documentService.findById(document_id);
        if(document == null){
            throw new RuntimeException("document not found " + document_id);
        }
        return document.getVersions();
    }

    @GetMapping("/documents/versions/{version_id}")
    public DocumentVersion getCurrentVersion(@PathVariable int version_id){
        DocumentVersion documentVersion = documentVersionService.findById(version_id);
        if(documentVersion == null){
            throw new RuntimeException("documentVersion not found " + version_id);
        }
        return documentVersion;
    }

    @PostMapping(path ="/documents/{document_id}/version",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String addVersion(@RequestParam("file")MultipartFile file,
                             @RequestParam("title") String title,
                             @RequestParam("note") String note,
                             @RequestParam("uploadDate") Date uploadDate,
                             @RequestParam("mimeType") String mimeType,
                             @RequestParam("size") Long size,
                             @RequestParam("extension") String extension,
                             @RequestParam("docId") Long docId,
                             @RequestParam("userId") Integer userId
    ){
        DocumentVersion documentVersion = new DocumentVersion(title, mimeType, size, uploadDate, note, null, extension);

        User user = userService.findById(userId);
        Document document = documentService.findById(docId);
        documentVersion.setUser(user);
        documentVersion.setDocument(document);

        String id = null;
        try {
            id = fileStorageService.uploadFile(documentVersion, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    @GetMapping("/documents/version/{version_id}/content")
    public void downloadVersionContent(@PathVariable long version_id, HttpServletResponse response){
        DocumentVersion documentVersion = documentVersionService.findById(version_id);
        InputStream content = fileStorageService.downloadFile(documentVersion.getStorageId());
        response.setContentType(documentVersion.getMimeType());
        String filename = String.format("%s.%s",documentVersion.getDocument().getTitle(),documentVersion.getExtension());
        response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        try {
            IOUtils.copy(content, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @DeleteMapping("/documents/versions/{version_id}")
    public String deleteCurrentVersion(@PathVariable int version_id){
        DocumentVersion documentVersion = documentVersionService.findById(version_id);
        if(documentVersion == null){
            throw new RuntimeException("documentVersion not found " + version_id);
        }
        documentVersionService.deleteById(version_id);
        return "success";
    }

    //=============================SEARCH==================================

    @GetMapping("/documents/search/tag/{tag_title}")
    public List<TreeItemWrapper> searchByTag(@PathVariable String tag_title){
        Tag tag = tagService.findByTitle(tag_title);
        List<Document> documents = tag.getDocuments();
        List<TreeItemWrapper> treeItemWrappers = new ArrayList<>();
        for(Document document : documents){
            treeItemWrappers.add(new TreeItemWrapper(document));
        }

        return treeItemWrappers;
    }



}
