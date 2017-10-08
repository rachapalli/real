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

/**
 * This dao implementation class having all the commonly used methods.
 * 
 * @author umamaheswarar - Chetu
 * @version 1.0 - Oct 7, 2017
 */
@Repository(value = "baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(Class<T> clazz) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).list();
	}
	
	@SuppressWarnings("unchecked")
	public <X> X findUniqueByColumn(Class<T> clazz, String column, Object value) throws HibernateException {
		return (X) getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq(column, value)).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findByColumn(Class<T> clazz, String column, Object value) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq(column, value)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findByColumn(Class<T> clazz, String column, String... values) throws HibernateException {
		return (List<X>) getSessionFactory().getCurrentSession().createCriteria(clazz)
				.add(Restrictions.in(column, values)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findById(Class<T> clazz, final List<Long> ids) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.in("id", ids)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <X> X findById(Class<T> clazz, Long id) throws HibernateException {
		return (X) getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <X> List<X> findById(Class<T> clazz, final Long... id) throws HibernateException {
		return getSessionFactory().getCurrentSession().createCriteria(clazz)
				.add(Restrictions.in("id", new ArrayList<>(Arrays.asList(id)))).list();
	}

	public void flush() throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		currentSession.flush();
	}

	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	@Override
	public Serializable save(T paramT) throws HibernateException {
		final Session currentSession = getSessionFactory().getCurrentSession();
		final Serializable serializable = currentSession.save(paramT);
		currentSession.flush();
		return serializable;
	}

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
