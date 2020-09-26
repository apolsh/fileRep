package com.wasd.filerep.rest;

import com.wasd.filerep.entity.Document;
import com.wasd.filerep.entity.DocumentVersion;
import com.wasd.filerep.entity.User;
import com.wasd.filerep.service.DocumentService;
import com.wasd.filerep.service.DocumentVersionService;
import com.wasd.filerep.service.FileStorageService;
import com.wasd.filerep.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentRestController {

    private DocumentService documentService;
    private DocumentVersionService documentVersionService;
    private FileStorageService fileStorageService;

    @Autowired
    UserService userService;

    @Autowired
    public DocumentRestController(DocumentService documentService,
                                  DocumentVersionService documentVersionService,
                                  FileStorageService fileStorageService) {
        this.documentService = documentService;
        this.documentVersionService = documentVersionService;
        this.fileStorageService =fileStorageService;
    }

    @GetMapping("/")
    public List<Document> findAll(){
        return documentService.findAll();
    }

    @GetMapping("/{document_id}")
    public Document getDocument(@PathVariable int document_id){
        Document document = documentService.findById(document_id);
        if(document == null){
            throw new RuntimeException("document not found " + document_id);
        }
        return document;
    }

    @PostMapping("/")
    public Document addDocument(@RequestBody Document document){
        document.setId(0);
        documentService.save(document);
        return document;
    }

    @PutMapping("/")
    public Document updateDocument(@RequestBody Document document){
        documentService.save(document);
        return document;
    }

    @DeleteMapping("/{document_id}")
    public String deleteDocument(@PathVariable int document_id){
        Document document = documentService.findById(document_id);
        if(document == null){
            throw new RuntimeException("document not found " + document_id);
        }
        documentService.deleteById(document_id);
        return "success";
    }


    //======================VERSIONS=================================================


    @GetMapping("/{document_id}/versions")
    public List<DocumentVersion> getDocumentVersions(@PathVariable long document_id){
        Document document = documentService.findById(document_id);
        if(document == null){
            throw new RuntimeException("document not found " + document_id);
        }
        return document.getVersions();
    }

    @GetMapping("/versions/{version_id}")
    public DocumentVersion getCurrentVersion(@PathVariable int version_id){
        DocumentVersion documentVersion = documentVersionService.findById(version_id);
        if(documentVersion == null){
            throw new RuntimeException("documentVersion not found " + version_id);
        }
        return documentVersion;
    }

    @PostMapping(path ="/{document_id}/version",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String addVersion(@PathVariable Document document_id,
                             @RequestParam("file")MultipartFile file,
                             @RequestParam("filename") String filename,
                             @RequestParam("note")String note,
                             @RequestParam("mimeType") String mimeType
    ){
        String ext = FilenameUtils.getExtension(filename);

        DocumentVersion documentVersion = new DocumentVersion();
        documentVersion.setMimeType(mimeType);
        documentVersion.setSize(file.getSize());
        documentVersion.setUploadDate(new Date());

        //TODO:replace with real user
        User user = userService.findById(1);
        documentVersion.setUser(user);

        documentVersion.setNote(note);
        documentVersion.setDocument(document_id);
        documentVersion.setExtension(ext);
        String id = null;
        try {
            id = fileStorageService.uploadFile(documentVersion, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    @GetMapping("/version/{version_id}/content")
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

    @DeleteMapping("/versions/{version_id}")
    public String deleteCurrentVersion(@PathVariable int version_id){
        DocumentVersion documentVersion = documentVersionService.findById(version_id);
        if(documentVersion == null){
            throw new RuntimeException("documentVersion not found " + version_id);
        }
        documentVersionService.deleteById(version_id);
        return "success";
    }

}
