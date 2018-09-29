package com.dincrash.dao;

import com.dincrash.entities.IndexTable;

import java.util.List;

public interface IndexTableDAO {

    public List<IndexTable> listTable();
    public IndexTable find(int id);
    public void create (IndexTable indexTable);
    public void delete (int id);
    public void update (IndexTable indexTable);
}
