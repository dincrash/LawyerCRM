package com.dincrash.dao;


import com.dincrash.entities.DeloDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("documentDAO")
public class DocumentDAOImpl implements DocumentDAO {


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<DeloDocument> listTable() {

        List<DeloDocument> deloDocuments = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            deloDocuments = session.createQuery("from DeloDocument").list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return deloDocuments;
    }

    @Override
    public DeloDocument find(int id) {
        DeloDocument deloDocument = null;
        Session session = null;
        Transaction transaction = null;


        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            deloDocument = (DeloDocument) session.createQuery("from DeloDocument where id = :id")
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
        return deloDocument;
    }


    @Override
    public List<DeloDocument> findByName(String name) {
        List<DeloDocument> deloDocument = null;
        Session session = null;
        Transaction transaction = null;


        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from DeloDocument where name like concat('%',:dq,'%')";
            Query q1 = session.createQuery(hql);


            q1.setParameter("dq",name);
            deloDocument = q1.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return deloDocument;
    }

    @Override
    public void create(DeloDocument deloDocument) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(deloDocument);
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
    public void update(DeloDocument deloDocument) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(deloDocument);
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

    @Override
    public List<DeloDocument> findAllbyId(int docid) {
        List<DeloDocument> deloDocument = null;
        Session session = null;
        Transaction transaction = null;


        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from DeloDocument where docid like concat('%',:dq,'%')";
            Query q1 = session.createQuery(hql);


            q1.setParameter("dq",docid);
            deloDocument = q1.list();





            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return deloDocument;
    }
}
