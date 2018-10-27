package com.dincrash.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "indextable")
public class IndexTable {
    private int id;
    private Date datecourt;
    private int status;
    private String comment;
    private Date date;
    private String name;
    private String fio;
    private Set<DeloDocument> deloDocument=new HashSet<DeloDocument>();
    private long telephone1;
    private long telephone2;
    private String time;

    @OneToMany(mappedBy = "indexTable", cascade = CascadeType.ALL)
    public Set<DeloDocument> getDeloDocument() {
        return deloDocument;
    }

    public void setDeloDocument(Set<DeloDocument> deloDocument) {
        this.deloDocument = deloDocument;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "fio", nullable = false, length = 250)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    @Basic
    @Column(name = "datecourt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getDatecourt() {
        return datecourt;
    }

    public void setDatecourt(Date datecourt) {
        this.datecourt = datecourt;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Basic
    @Column(name = "telephone1")
    public long getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(long telephone1) {
        this.telephone1 = telephone1;
    }
    @Basic
    @Column(name = "telephone2")
    public long getTelephone2() {
        return telephone2;
    }
    public void setTelephone2(long telephone2) {
        this.telephone2 = telephone2;
    }

    @Basic
    @Column(name ="time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
