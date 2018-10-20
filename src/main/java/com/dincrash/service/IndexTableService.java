package com.dincrash.service;

import com.dincrash.entities.IndexTable;

import java.util.List;

public interface IndexTableService {

    public List<IndexTable> listTable();
    public IndexTable find(int id);
    public void create (IndexTable indexTable);
    public void delete (int id);
    public void update (IndexTable indexTable);
    public List<IndexTable> listArchive();
    public List<IndexTable> listSuperArchive();

}
