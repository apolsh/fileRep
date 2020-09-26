package com.wasd.filerep.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="doc_versions")
public class NewEntityDV {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="user_id")
    private int user_id;
    @Column(name="doc_id")
    private long doc_id;
    @Column(name="mime_type")
    private String mimeType;
    @Column(name = "upload_date")
    private Date datetime;
    @Column(name = "size")
    private long size;
    @Column(name = "note")
    private String note;

    public NewEntityDV() {
    }

    public NewEntityDV(int user_id, long doc_id, String mimeType, Date datetime, long size, String note) {
        this.user_id = user_id;
        this.doc_id = doc_id;
        this.mimeType = mimeType;
        this.datetime = datetime;
        this.size = size;
        this.note = note;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(long doc_id) {
        this.doc_id = doc_id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
