package org.example.repository.hibernate;

import org.example.exception.DataAccessLayerException;
import org.example.model.Skill;
import org.example.repository.SkillRepo;
import org.example.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class HibernateSkillRepoImpl implements SkillRepo {
    @Override
    public boolean save(Skill skill) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(skill);
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
            Skill skill = new Skill();
            skill.setId(id);
            session.delete(skill);
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
    public Skill findById(Long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Skill.class, id);
    }

    @Override
    public boolean update(Skill skill) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Skill updSkill = new Skill();
            updSkill.setId(skill.getId());
            updSkill.setName(skill.getName());
            session.update(skill);
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
    public List<Skill> findAll() {
        List objects = null;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Skill");
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
