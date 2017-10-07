/* 
 * ===========================================================================
 * File Name BaseDaoImpl.java
 * 
 * Created on Oct 04, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of Real_Estate. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of Real_Estate.
 *
 * Copyright (C) BaseDaoImpl. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: IBaseDao.java,v $
 * ===========================================================================
 */
package com.property.buyer.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.property.buyer.dao.IBaseDao;

@Repository(value = "baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	/**
	 * Delete entity object related to specified bean object passed as method
	 * argument
	 * 
	 * @author gauravk
	 * @param paramT
	 *            A Object type
	 * @exception HibernateException
	 * @return Serializable
	 */
	@Override
	public void delete(T paramT) throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		currentSession.delete(paramT);
		currentSession.flush();
	}

	/**
	 * Delete entity object based on the id that is related to a specific bean
	 * 
	 * @author gauravk
	 * @param clazz
	 * @param id
	 * @throws HibernateException
	 */
	@Override
	public boolean deleteById(Class<T> clazz, Long id) throws HibernateException {
		T obj = findById(clazz, id);
		if (obj != null) {
			final Session currentSession = getSessionFactory().getCurrentSession();
			currentSession.delete(obj);
			currentSession.flush();
			return true;
		}
		return false;
	}

	/**
	 * Find and retrieve list of object as per provided sql param string and
	 * parameter with maximum records size define
	 * 
	 * @author gauravk
	 * @param paramString
	 *            A String object contain
	 * @param paramMap
	 *            A Map<String, Object> object contain
	 * @param maxResults
	 *            A integer type variable contain
	 * @exception HibernateException
	 * @return List of type object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(Class<T> clazz) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).list();
	}

	/**
	 * Find and retrieve list of object as per provided sql param string and
	 * parameter with maximum records size define
	 * 
	 * @author gauravk
	 * @param paramString
	 *            A String object contain
	 * @param paramMap
	 *            A Map<String, Object> object contain
	 * @param maxResults
	 *            A integer type variable contain
	 * @exception HibernateException
	 * @return List of type object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findByColumn(Class<T> clazz, String column, Object value) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq(column, value)).list();
	}

	/**
	 * Find and retrieve list of objects as per provided sql param string and
	 * parameter with maximum records size define
	 * 
	 * @author vikashk3
	 * @date Apr 28, 2017
	 * @return <code>List<X></code> List of type object
	 * @param column
	 *            string type column
	 * @param values
	 *            string type values
	 * @throws HibernateException
	 *             hibernate exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findByColumn(Class<T> clazz, String column, String... values) throws HibernateException {
		return (List<X>) getSessionFactory().getCurrentSession().createCriteria(clazz)
				.add(Restrictions.in(column, values)).list();
	}

	/**
	 * Find and retrieve list of object as per provided sql param string and
	 * parameter with maximum records size define
	 * 
	 * @author gauravk
	 * @param paramString
	 *            A String object contain
	 * @param paramMap
	 *            A Map<String, Object> object contain
	 * @param maxResults
	 *            A integer type variable contain
	 * @exception HibernateException
	 * @return List of type object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findById(Class<T> clazz, final List<Long> ids) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.in("id", ids)).list();
	}

	/**
	 * Find unique object using param query and parameter
	 * 
	 * @author gauravk
	 * @param paramString
	 *            A String object contain
	 * @param paramMap
	 *            A Map<String, Object> object contain
	 * @exception HibernateException
	 * @return Object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <X> X findById(Class<T> clazz, Long id) throws HibernateException {
		return (X) getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	/**
	 * Find and retrieve list of object as per provided sql param string and
	 * parameter with maximum records size define
	 * 
	 * @author gauravk
	 * @param paramString
	 *            A String object contain
	 * @param paramMap
	 *            A Map<String, Object> object contain
	 * @param maxResults
	 *            A integer type variable contain
	 * @exception HibernateException
	 * @return List of type object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findById(Class<T> clazz, final Long... id) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz)
				.add(Restrictions.in("id", new ArrayList<>(Arrays.asList(id)))).list();
	}

	/**
	 * Find and retrieve unique object as per provided sql param string and
	 * parameter with maximum records size define
	 * 
	 * @author gauravk
	 * @param paramString
	 *            A String object contain
	 * @param paramMap
	 *            A Map<String, Object> object contain
	 * @param maxResults
	 *            A integer type variable contain
	 * @exception HibernateException
	 * @return List of type object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <X> X findUniqueByColumn(Class<T> clazz, String column, Object value) throws HibernateException {
		return (X) getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq(column, value))
				.uniqueResult();
	}

	/**
	 * Force this session to flush. Must be called at the end of a unit of work,
	 * before committing the transaction and closing the session
	 * 
	 * @author vikashk3
	 * @date May 25, 2017
	 * @return <code>void</code>
	 * @throws HibernateException
	 */
	public void flush() throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		currentSession.flush();
	}

	/**
	 * This method id used to get the session object
	 * 
	 * @author gauravk
	 * @return session session object
	 */
	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	/**
	 * Save if not persit or update if already persist entity object in db
	 *
	 * @author gauravk
	 * @param paramT
	 *            A Object type
	 * @exception HibernateException
	 * @return void
	 */
	@Override
	@SuppressWarnings({ "unchecked" })
	public T merge(T paramT) throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		return (T) currentSession.merge(paramT);
	}

	/**
	 * Save entity object related to specified bean object passed as method
	 * argument
	 * 
	 * @author gauravk
	 * @param paramT
	 *            A Object type
	 * @exception HibernateException
	 * @return Serializable
	 */
	@Override
	public Serializable save(T paramT) throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		final Serializable serializable = currentSession.save(paramT);
		currentSession.flush();
		return serializable;
	}

	/**
	 * Save if not persit or update if already persist entity object in db
	 * 
	 * @author gauravk
	 * @param paramT
	 *            A Object type
	 * @exception HibernateException
	 * @return void
	 */
	@Override
	public void saveOrUpdate(T paramT) throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		currentSession.saveOrUpdate(paramT);
		currentSession.flush();
	}

	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
