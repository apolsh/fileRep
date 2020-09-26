package com.wasd.filerep.rest;

import com.wasd.filerep.entity.Document;
import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
public class FolderRestController {

    FolderService folderService;

    @Autowired
    public FolderRestController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/")
    public List<Folder> findAll(){
        return folderService.findAll();
    }

    @GetMapping("/{folder_id}")
    public Folder getFolder(@PathVariable int folder_id){
        Folder folder = folderService.findById(folder_id);
        if(folder == null){
            throw new RuntimeException("folder not found " + folder_id);
        }
        return folder;
    }

    @GetMapping("/{folder_id}/documents")
    public List<Document> getDocumentsInFolder(@PathVariable int folder_id){
        Folder folder = folderService.findById(folder_id);
        if(folder == null){
            throw new RuntimeException("folder not found " + folder_id);
        }
        return folder.getDocuments();
    }

    @PostMapping("/")
    public Folder addFolder(@RequestBody Folder folder){
        folder.setId(0);
        folderService.save(folder);
        return folder;
    }

    @PutMapping("/")
    public Folder updateFolder(@RequestBody Folder folder){
        folderService.save(folder);
        return folder;
    }

    @DeleteMapping("/{folder_id}")
    public String deleteFolder(@PathVariable int folder_id){
        Folder folder = folderService.findById(folder_id);
        if(folder == null){
            throw new RuntimeException("folder not found " + folder_id);
        }
        folderService.deleteById(folder_id);
        return "success";
    }
}
