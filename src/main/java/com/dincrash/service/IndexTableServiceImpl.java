package com.dincrash.service;

import com.dincrash.dao.IndexTableDAO;
import com.dincrash.entities.IndexTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("indexTableService")
public class IndexTableServiceImpl implements IndexTableService {


    @Autowired
    private IndexTableDAO indexTableDAO;
    @Override
    public List<IndexTable> listTable() {
        return this.indexTableDAO.listTable();
    }

    @Override
    public IndexTable find(int id) {
        return this.indexTableDAO.find(id);
    }

    @Override
    public void create(IndexTable indexTable) {
        this.indexTableDAO.create(indexTable);

    }

    @Override
    public void delete(int id) {
        this.indexTableDAO.delete(id);
    }

    @Override
    public void update(IndexTable indexTable) {
        this.indexTableDAO.update(indexTable);
    }


    @Override
    public List<IndexTable> listArchive() {
        return this.indexTableDAO.listArchive();
    }
    @Override
    public List<IndexTable> listSuperArchive() {
        return this.indexTableDAO.listSuperArchive();
    }



}
