package com.exchangerates.server.dao.impl;


import com.exchangerates.server.dao.BasicCrudDao;
import com.exchangerates.server.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public abstract class AbstractHibernateDAO<E> implements BasicCrudDao<E>{
    protected final Class<E> entityClass;

    public AbstractHibernateDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected Session session() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    public void save(E entity) {
        Transaction transaction = null;
        try {
            transaction = session().beginTransaction();
            session().save(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if(session().isOpen()) {
                session().close();
            }
        }
    }

    @Override
    public List<E> getAll() {
        Transaction transaction = null;
        List<E> data = null;
        try {
            transaction = session().beginTransaction();
            data =  session().createCriteria(entityClass).list();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if(session().isOpen()) {
                session().close();
            }
        }
        return  data;
    }

    @Override
    public E get(String property, Object value) {
        Transaction transaction = null;
        E data = null;
        try {
            transaction = session().beginTransaction();
            data = (E)session().createCriteria(entityClass).add(Restrictions.eq(property, value)).uniqueResult();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session().isOpen()) {
                session().close();
            }
        }
        return data;
    }

    @Override
    public List<E> getList(String property, Object value) {
        Transaction transaction = null;
        List<E> data = null;
        try {
            transaction = session().beginTransaction();
            data = session().createCriteria(entityClass).add(Restrictions.eq(property, value)).list();
            transaction.commit();
        } catch(RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session().isOpen()) {
                session().close();
            }
        }
        return data;
    }
}
