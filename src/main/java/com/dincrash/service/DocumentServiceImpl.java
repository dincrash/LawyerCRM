package com.dincrash.service;

import com.dincrash.dao.DocumentDAO;
import com.dincrash.entities.DeloDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("documentService")
public class DocumentServiceImpl implements DocumentService {


    @Autowired
    private DocumentDAO documentDAO;
    @Override
    public List<DeloDocument> listTable() {
        return this.documentDAO.listTable();
    }

    @Override
    public DeloDocument find(int id) {
        return this.documentDAO.find(id);
    }

    @Override
    public void create(DeloDocument DeloDocument) {
        this.documentDAO.create(DeloDocument);

    }

    @Override
    public void delete(int id) {
        this.documentDAO.delete(id);
    }

    @Override
    public void update(DeloDocument deloDocument) {
        this.documentDAO.update(deloDocument);
    }


    @Override
    public List<DeloDocument> findAllbyId(int docid) {
        return this.documentDAO.findAllbyId(docid);
    }
}
