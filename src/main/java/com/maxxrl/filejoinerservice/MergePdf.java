package com.maxxrl.filejoinerservice;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class MergePdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mergeId;
    @Lob
    private byte[] data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMergeId() {
        return mergeId;
    }

    public void setMergeId(String mergeId) {
        this.mergeId = mergeId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}