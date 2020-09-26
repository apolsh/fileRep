package com.wasd.filerep.wrappers.rest;

import com.wasd.filerep.entity.Document;
import com.wasd.filerep.entity.Folder;

import java.util.ArrayList;
import java.util.List;

public class TreeItemWrapper {

    private String id;
    private String name;
    private String type;
    List<TreeItemWrapper> children= new ArrayList<>();

    public TreeItemWrapper(String id, String name) {
        this.id = id;
        this.name = name;
        this.type = "folder";
        this.children = new ArrayList<>();
    }

    public TreeItemWrapper(Document document) {
        this.id = String.format("%s_%d","document", document.getId()) ;
        this.name = document.getTitle();
        this.type = "document";
        this.children=null;
    }

    public TreeItemWrapper(Folder folder) {
        TreeItemWrapper treeItemWrapper = folderToTreeItem(folder);

        this.id = treeItemWrapper.getId();
        this.name = treeItemWrapper.getName();
        this.type = treeItemWrapper.getType();
        this.children = treeItemWrapper.getChildren();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeItemWrapper> getChildren() {
        return children;
    }

    public void setChildren(List<TreeItemWrapper> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addChildren(TreeItemWrapper item){
        if(item != null)
            this.children.add(item);
    }

    private TreeItemWrapper folderToTreeItem(Folder folder){
        TreeItemWrapper treeItemWrapper = new TreeItemWrapper(
                String.format("%s_%d","folder", folder.getId()),
                        folder.getTitle());
        List<Document> innerDocuments = folder.getDocuments();
        if(innerDocuments!= null && innerDocuments.size() > 0){
            for(Document document : innerDocuments){
                treeItemWrapper.addChildren(new TreeItemWrapper(document));
            }
        }
        List<Folder> innerFolders = folder.getFolders();
        if(innerFolders != null && innerFolders.size() > 0){
            for(Folder innerFolder : innerFolders){
                treeItemWrapper.addChildren(folderToTreeItem(innerFolder));
            }
        }
        return treeItemWrapper;
    }

    public void addChildrenDocumentsRoot(List<Document> documents) {
        if(documents!= null && documents.size() > 0){
            for(Document document : documents){
                if(document.getFolderId() == null){
                    this.addChildren(new TreeItemWrapper(document));
                }

            }
        }
    }

    public void addChildrenFolders(List<Folder> folders) {
        if(folders != null && folders.size() > 0){
            for(Folder innerFolder : folders){
                this.addChildren(folderToTreeItem(innerFolder));
            }
        }
    }



}
