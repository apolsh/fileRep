package com.wasd.filerep.wrappers.rest.requests;

import com.wasd.filerep.entity.Folder;

public class CreateFolderRequest {

    private Folder folder;
    private Integer parentId;
    private Integer sectionId;

    public CreateFolderRequest(Folder folder, Integer parentId, Integer sectionId) {
        this.folder = folder;
        this.parentId = parentId;
        this.sectionId = sectionId;
    }

    public CreateFolderRequest() {
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
}
