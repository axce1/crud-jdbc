package org.example.repository.hibernate;

import org.example.exception.DataAccessLayerException;
import org.example.model.Developer;
import org.example.repository.DeveloperRepo;
import org.example.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class HibernateDeveloperRepoImpl implements DeveloperRepo {
    @Override
    public boolean save(Developer developer) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(developer);
            tx.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new DataAccessLayerException("Transation error");
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Developer developer = new Developer();
            developer.setId(id);
            session.delete(developer);
            tx.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new DataAccessLayerException("Transation error");
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public Developer findById(Long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Developer.class, id);
    }

    @Override
    public boolean update(Developer developer) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.update(developer);
            tx.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new DataAccessLayerException("Transation error");
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public List<Developer> findAll() {
        List objects = null;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from " + Developer.class);
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new DataAccessLayerException("Transation error");
        } finally {
            session.close();
        }
        return objects;
    }
}
