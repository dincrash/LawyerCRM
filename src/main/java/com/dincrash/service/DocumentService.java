package com.dincrash.service;

import com.dincrash.entities.DeloDocument;
import com.dincrash.entities.IndexTable;

import java.util.List;

public interface DocumentService {

    public List<DeloDocument> listTable();
    public DeloDocument find(int id);
    public void create(DeloDocument deloDocument);
    public void delete(int id);
    public void update(DeloDocument deloDocument);
    public List<DeloDocument> findAllbyId(int docid);
}
