package com.dincrash.entities;

import javax.persistence.*;

@Entity
@Table(name = "delodocument")
public class DeloDocument {


    private Integer id;
    private String name;
    private String description;
    private String type;
    private byte[] content;
    private IndexTable indexTable;




    @ManyToOne(optional = false)
    @JoinColumn(name = "docid")
    public IndexTable getIndexTable() {
        return indexTable;
    }

    public void setIndexTable(IndexTable indexTable) {
        this.indexTable = indexTable;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "name", length=100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}