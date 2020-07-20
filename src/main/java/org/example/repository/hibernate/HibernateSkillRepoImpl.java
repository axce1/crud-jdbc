package org.example.repository.hibernate;

import org.example.model.Skill;
import org.example.repository.SkillRepo;
import org.example.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class HibernateSkillRepoImpl implements SkillRepo {
    @Override
    public boolean save(Skill skill) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(skill);
        tx1.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(id);
        tx1.commit();
        session.close();
    }

    @Override
    public Skill findById(Long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Skill.class, id);

    }

    @Override
    public boolean update(Skill skill) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(skill);
        tx1.commit();
        session.close();
    }

    @Override
    public Set<Skill> findAll() {
        return null;
    }
}
