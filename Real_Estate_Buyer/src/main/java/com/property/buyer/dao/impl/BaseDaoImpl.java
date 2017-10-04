/* 
 * ===========================================================================
 * File Name BaseDaoImpl.java
 * 
 * Created on 15-Jun-2017
 *
 * This code contains copyright information which is the proprietary property
 * of GirasolPayments-Admin. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of GirasolPayments-Admin.
 *
 * Copyright (C) GirasolPayments-Admin. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: BaseDaoImpl.java,v $
 * ===========================================================================
 */
package com.property.buyer.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.property.buyer.dao.IBaseDao;

/**
 *This is a implementation of base dao ,it is used to implement the
 *basic operation of entity
 * 
 * @author prabinar
 * @version 1.0 - 17-Jun-2017
 */
@Repository(value="baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	
	
	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(final SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public <X> X findById(final Class<T> clazz, final Long objId) throws HibernateException {
		return (X) getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq("id", objId))
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public <X> List<X> findById(final Class<T> clazz, final List<Long> ids) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.in("id", ids)).list();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> findByColumn(final Class<T> clazz, final String column, final Object value) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq(column, value)).list();
	}

	
	@SuppressWarnings("unchecked")
	public <X> X findUniqueByColumn(final Class<T> clazz, final String column, final Object value) throws HibernateException {
		return (X) getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq(column, value))
				.uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(final Class<T> clazz) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).list();
	}

	
	public void deleteById(final Class<T> clazz, final Long objId) throws HibernateException {
		T obj = findById(clazz, objId);
		if (obj != null) {
			final Session currentSession = getSessionFactory().getCurrentSession();
			currentSession.delete(obj);
			currentSession.flush();
		}
	}

	
	@SuppressWarnings({ "unchecked" })
	public T merge(final T paramT) throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		return (T) currentSession.merge(paramT);
	}

	
	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> findByTwoOredColumn(final Class<T> clazz, String emailValue, final String cellPhoneValue) throws HibernateException {
		final org.hibernate.Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(clazz);
		final Criterion restForEmail = Restrictions.eq("email", emailValue);
		final Criterion restForCellphone = Restrictions.eq("cellPhone", cellPhoneValue);
		criteria.add(Restrictions.or(restForEmail, restForCellphone));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> findByIdAndColumn(final Class<T> clazz, final String idColumn, final String column, final Long idValue,
			final String columnValue) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.ne(column, columnValue))
				.add(Restrictions.eq(idColumn, idValue)).list();
	}

	public Serializable save(final T paramT) throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		final Serializable serializable = currentSession.save(paramT);
		currentSession.flush();
		return serializable;
	}

	@SuppressWarnings("unchecked")
	public <X> X findUnique(final String paramString, final Map<String, Object> paramMap) throws HibernateException {
		return (X) createQuery(paramString, paramMap, null).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public <X> X findUnique(final String paramString, final Map<String, Object> paramMap, final Class<T> clazz) throws HibernateException {
		return (X) createQuery(paramString, paramMap, clazz).uniqueResult();
		
	}

	@SuppressWarnings("rawtypes")
	protected Query createQuery(final String queryString, final Map<String, Object> values, final Class clazz) throws HibernateException {
		final Session session = getSessionFactory().getCurrentSession();
		final Query query = session.createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		if (clazz != null) {
			query.setResultTransformer(Transformers.aliasToBean(clazz));	
		}
		return query;
	}

	public void saveOrUpdate(T paramT) throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		currentSession.saveOrUpdate(paramT);
		currentSession.flush();
	}

	@SuppressWarnings("rawtypes")
	protected Query createQuery(final String queryString, final Map<String, Object> values, final Class clazz, final int maxResults)
			throws HibernateException {
		final Query query = createQuery(queryString, values, clazz);
		query.setMaxResults(maxResults);
		return query;
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> find(final String paramString,final Map<String, Object> paramMap, final int maxResults)
			throws HibernateException {
		return createQuery(paramString, paramMap, null, maxResults).list();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> find(final String paramString, final Map<String, Object> paramMap) throws HibernateException {
		@SuppressWarnings("rawtypes")
	    List list = new ArrayList();
	    list = createQuery(paramString, paramMap, null).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(final String queryString) throws HibernateException {
		final Session session = getSessionFactory().getCurrentSession();
		final Query query = session.createQuery(queryString).setCacheable(true);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(final String queryString, final Map<String , Object> map ) throws HibernateException {
		final Session session = getSessionFactory().getCurrentSession();
		final Query query = session.createQuery(queryString).setCacheable(true);
		if(map != null){
			query.setProperties(map);
		}
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(final String queryString, final Class<T> className, final Map<String,Object> map) throws HibernateException {
		final Session session = getSessionFactory().getCurrentSession();
		final Query query = session.createQuery(queryString).setCacheable(true);
		if(map != null){
			query.setProperties(map);
		}
		query.setMaxResults(100);
		query.setResultTransformer(Transformers.aliasToBean(className));
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(final String queryString, final Class<T> className) throws HibernateException {
		final Session session = getSessionFactory().getCurrentSession();
		final Query query = session.createQuery(queryString).setCacheable(true);
		query.setMaxResults(100);
		query.setResultTransformer(Transformers.aliasToBean(className));
		return query.list();
	}

	public Object executSQL(final String sql) throws Exception {
		final Transaction tnx = initializeTransaction();
		final SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		final Object result = query.uniqueResult();
		commitTransaction(tnx);
		return result;
	}

	public Transaction initializeTransaction() throws HibernateException {
		final Session session = getSessionFactory().getCurrentSession();
		Transaction tnx = session.getTransaction();
		if (tnx == null) {
			tnx = session.beginTransaction();
		} else {
			if (!tnx.isActive()) {
				tnx = session.beginTransaction();
			}
		}
		return tnx;
	}

	public void commitTransaction(final Transaction transaction) {
		if (transaction != null && transaction.isActive()) {
			transaction.commit();
		}

	}

	public void rollBackTransaction(final Transaction transaction) {
		if (transaction != null && transaction.isActive()) {
			transaction.rollback();
		}
	}

	public void delete(final T paramT) throws HibernateException, SQLException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		currentSession.delete(paramT);
		currentSession.flush();
	}
 
    public void delete(final String paramString, final Map<String, Object> paramMap) throws HibernateException, SQLException {
        final Session session = getSessionFactory().getCurrentSession();
        final Query query = session.createQuery(paramString);
        if (paramMap != null) {
            query.setProperties(paramMap);
            query.executeUpdate();
        }
        session.flush();
    }
	
	@SuppressWarnings("unchecked")
	public List<Long> find(final String string,final Map<String, Object> paramMap,final Map<String, List<Long>> listMap) {
		final Session session = getSessionFactory().getCurrentSession();
		final Query query = session.createQuery(string).setCacheable(true);
		if(paramMap != null){
			query.setProperties(paramMap);
		}
		if(listMap != null && !listMap.isEmpty()){
			for (Entry<String, List<Long>> entry : listMap.entrySet())			{
				query.setParameterList(entry.getKey(), entry.getValue());
			}
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(final String queryString, final Map<String, Object> map, final int start, final int amount, final Class<T> className)
			throws HibernateException {
		final Session session = getSessionFactory().getCurrentSession();
		final Query query = session.createQuery(queryString).setCacheable(true);
		if (map != null) {
			query.setProperties(map);
		}
		query.setFirstResult(start).setMaxResults(amount);
		if (className != null) {
			query.setResultTransformer(Transformers.aliasToBean(className));
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(final Class<T> clazz, final String fieldName, final List<Long> idList) throws HibernateException {
		final org.hibernate.Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(clazz);
		criteria.add(Restrictions.in(fieldName, idList));
		return criteria.list();
	}

}
