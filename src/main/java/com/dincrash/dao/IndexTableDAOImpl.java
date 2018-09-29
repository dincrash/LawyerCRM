package com.dincrash.dao;


import com.dincrash.entities.IndexTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("indexTableDAO")
public class IndexTableDAOImpl implements IndexTableDAO {


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<IndexTable> listTable() {

        List<IndexTable> indexTables = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            indexTables = session.createQuery("from IndexTable").list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return indexTables;
    }

    @Override
    public IndexTable find(int id) {
        IndexTable indexTable = null;
        Session session = null;
        Transaction transaction = null;


        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            indexTable = (IndexTable) session.createQuery("from IndexTable where id = :id")
                    .setInteger("id", id)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return indexTable;
    }

    @Override
    public void create(IndexTable indexTable) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(indexTable);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();

            }
        } finally {
            session.close();
        }
    }

    @Override
    public void update(IndexTable indexTable) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(indexTable);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();

            }
        } finally {
            session.close();
        }
    }
    @Override
    public void delete(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(this.find(id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();

            }
        } finally {
            session.close();
        }
    }
}
