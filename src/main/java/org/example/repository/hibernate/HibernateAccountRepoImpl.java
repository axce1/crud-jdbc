package org.example.repository.hibernate;

import org.example.exception.DataAccessLayerException;
import org.example.model.Account;
import org.example.repository.AccountRepo;
import org.example.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateAccountRepoImpl implements AccountRepo {
    @Override
    public boolean save(Account account) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(account);
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
            Account account = new Account();
            account.setId(id);
            session.delete(account);
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
    public Account findById(Long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Account.class, id);
    }

    @Override
    public boolean update(Account account) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(account);
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
    public List<Account> findAll() {
        List objects = null;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Account");
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
