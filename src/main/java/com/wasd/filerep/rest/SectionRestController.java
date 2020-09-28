package com.wasd.filerep.rest;

import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;
import com.wasd.filerep.service.SectionService;
import com.wasd.filerep.wrappers.rest.TreeItemWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SectionRestController {

    private SectionService sectionService;

    @Autowired
    public SectionRestController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/sections")
    public List<Section> findAll(){
        List<Section> all = sectionService.findAll();
        return all;
    }

    @GetMapping("/sections/{section_id}")
    public Section getSection(@PathVariable int section_id){
        Section section = sectionService.findById(section_id);
        if(section == null){
            throw new RuntimeException("section not found " + section_id);
        }
        return section;
    }

    @GetMapping("/sections/{section_id}/tree")
    public TreeItemWrapper getSessionFolders(@PathVariable int section_id){
        Section section = sectionService.findById(section_id);

        if(section == null){
            throw new RuntimeException("section not found " + section_id);
        }
        TreeItemWrapper treeItemWrapper = new TreeItemWrapper("root", "Корневая директория");
        List<Folder> filtered = section.getFolders().stream().filter(folder -> folder.getParentId() == null).collect(Collectors.toList());
        treeItemWrapper.addChildrenFolders(filtered);
        treeItemWrapper.addChildrenDocumentsRoot(section.getDocuments());
        return treeItemWrapper;
    }

    @PostMapping("/sections")
    public Section addSection(@RequestBody Section section){
        section.setId(0);
        sectionService.save(section);
        return section;
    }

    @PutMapping("/sections")
    public Section updateSection(@RequestBody Section section){
        sectionService.save(section);
        return section;
    }

    @DeleteMapping("/sections/{section_id}")
    public String deleteSection(@PathVariable int section_id){
        Section section = sectionService.findById(section_id);
        if(section == null){
            throw new RuntimeException("section not found " + section_id);
        }
        sectionService.deleteById(section_id);
        return "success";
    }


}
