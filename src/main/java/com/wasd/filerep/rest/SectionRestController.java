package com.wasd.filerep.rest;

import com.wasd.filerep.entity.Folder;
import com.wasd.filerep.entity.Section;
import com.wasd.filerep.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/sections/{section_id}/folders")
    public List<Folder> getSessionFolders(@PathVariable int section_id){
        Section section = sectionService.findById(section_id);
        if(section == null){
            throw new RuntimeException("section not found " + section_id);
        }
        return section.getFolders();
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
