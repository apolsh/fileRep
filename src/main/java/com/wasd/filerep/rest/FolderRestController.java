package com.wasd.filerep.rest;

import com.wasd.filerep.entity.Document;
import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;
import com.wasd.filerep.service.FolderService;
import com.wasd.filerep.service.SectionService;
import com.wasd.filerep.wrappers.rest.requests.CreateFolderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FolderRestController {

    private FolderService folderService;
    private SectionService sectionService;

    @Autowired
    public FolderRestController(FolderService folderService, SectionService sectionService) {
        this.folderService = folderService;
        this.sectionService = sectionService;
    }

    @GetMapping("/folders")
    public List<Folder> findAll(){
        return folderService.findAll();
    }

    @GetMapping("/folders/{folder_id}")
    public Folder getFolder(@PathVariable int folder_id){
        Folder folder = folderService.findById(folder_id);
        if(folder == null){
            throw new RuntimeException("folder not found " + folder_id);
        }
        return folder;
    }

    @GetMapping("/folders/{folder_id}/documents")
    public List<Document> getDocumentsInFolder(@PathVariable int folder_id){
        Folder folder = folderService.findById(folder_id);
        if(folder == null){
            throw new RuntimeException("folder not found " + folder_id);
        }
        return folder.getDocuments();
    }

    @PostMapping("/folders")
    public Folder addFolder(@RequestBody Folder folder){
        folder.setId(0);
        folderService.save(folder);
        return folder;
    }

    @PutMapping("/folders")
    public Folder updateFolder(@RequestBody Folder folder){
        folderService.save(folder);
        return folder;
    }

    @DeleteMapping("/folders/{folder_id}")
    public String deleteFolder(@PathVariable int folder_id){
        Folder folder = folderService.findById(folder_id);
        if(folder == null){
            throw new RuntimeException("folder not found " + folder_id);
        }
        folderService.deleteById(folder_id);
        return "success";
    }
}
