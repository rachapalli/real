/* 
 * ===========================================================================
 * File Name IBaseDao.java
 * 
 * Created on Oct 04, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of Real_Estate. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of Real_Estate.
 *
 * Copyright (C) Real_Estate. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: IBaseDao.java,v $
 * ===========================================================================
 */
package com.property.buyer.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IBaseDao<T> {

	<X> List<X> findById(final Class<T> clazz, final List<Long> ids) throws HibernateException;

	<X> List<X> findByColumn(final Class<T> clazz, final String column, final Object value) throws HibernateException;

	<X> X findUniqueByColumn(final Class<T> clazz, final String column, final Object value) throws HibernateException;

	<X> List<X> findAll(final Class<T> clazz) throws HibernateException;

	Session getCurrentSession();

	<X> List<X> findByIdAndColumn(final Class<T> clazz, final String idColumn, final String column, final Long idValue,
			String columnValue) throws HibernateException;

	Serializable save(T paramT) throws HibernateException;

	<X> X findUnique(final String paramString, final Map<String, Object> paramMap) throws HibernateException;

	<X> X findUnique(final String paramString, final Map<String, Object> paramMap, final Class<T> clazz)
			throws HibernateException;

	void saveOrUpdate(final T paramT) throws HibernateException;

	SessionFactory getSessionFactory() throws HibernateException;

	<X> List<X> find(final String paramString, final Map<String, Object> paramMap, final int maxResults)
			throws HibernateException;

	<X> List<X> find(final String paramString, final Map<String, Object> paramMap) throws HibernateException;

	<X> List<X> findAll(final String paramString) throws HibernateException;

	<X> List<X> findAll(final String paramString, final Map<String, Object> map) throws HibernateException;

	<X> List<X> findAll(final String paramString, final Class<T> className) throws HibernateException;

	<X> List<X> findAll(final String paramString, final Class<T> className, final Map<String, Object> map)
			throws HibernateException;

	Object executSQL(final String sql) throws Exception;

	List<Long> find(final String string, final Map<String, Object> paramMap, final Map<String, List<Long>> listMap);

	<X> List<X> findAll(final String queryString, final Map<String, Object> map, final int start, final int amount,
			final Class<T> className) throws HibernateException;

	<X> List<X> findAll(final Class<T> clazz, String fieldName, final List<Long> idList) throws HibernateException;

}
