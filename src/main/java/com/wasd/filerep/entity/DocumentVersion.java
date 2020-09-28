package com.wasd.filerep.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doc_versions")
public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "size")
    private long size;

    @Column(name = "upload_date")
    private Date uploadDate;

    @Column(name = "note")
    private String note;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne()
    @JoinColumn(name = "doc_id")
    private Document document;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "storage_id")
    private String storageId;

    @Column(name = "extension")
    private String extension;

    @Column(name = "doc_id")
    private long docId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document documentId) {
        this.document = documentId;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    @Override
    public String toString() {
        return "DocumentVersion{" +
                "id='" + id + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", size=" + size +
                ", uploadDate=" + uploadDate +
                ", note='" + note + '\'' +
                ", user=" + user +
                '}';
    }
}
